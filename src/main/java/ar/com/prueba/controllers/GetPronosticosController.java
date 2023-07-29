package ar.com.prueba.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import ar.com.deserialize.Match;
import ar.com.deserialize.Partido;
import ar.com.deserialize.Pronostico;
import ar.com.deserialize.UserTorneo;
import ar.com.deserialize.Team;
import ar.com.prueba.dao.iTeamDAO;
import ar.com.prueba.dao.iPronosticoDAO;
import ar.com.prueba.dao.iMatchDAO;
import ar.com.prueba.dao.iUserTorneoDAO;
import ar.com.prueba.dao.implement.MatchDAOMysqlImpl;
import ar.com.prueba.dao.implement.PronosticoDAOMysqlImpl;
import ar.com.prueba.dao.implement.TeamDAOMysqlImpl;
import ar.com.prueba.dao.implement.UserTorneoDAOMysqlImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MisPronosticos")
public class GetPronosticosController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    HttpSession session = req.getSession();
	    String userId = (String) session.getAttribute("uid");
	    
	    iPronosticoDAO dao = new PronosticoDAOMysqlImpl();
	    List<Pronostico> pronosticos = new ArrayList<>();
	    
	    iMatchDAO daoMatch = new MatchDAOMysqlImpl();
	    iTeamDAO daoTeam = new TeamDAOMysqlImpl();
	    iUserTorneoDAO daoUserTorneo = new UserTorneoDAOMysqlImpl();
	    
	    try {
			pronosticos = dao.getByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    List<Partido> partidos = new ArrayList<>();
	    Map<Long,Integer> mapPartidoPuntos = new HashMap<Long,Integer>();
	    Long puntosTorneo = Long.valueOf(0);
	    for (Pronostico pronostico: pronosticos) {
	    	Long matchId = pronostico.getMatchID();
	    	try {
				Partido partido = daoMatch.getPartidoById(matchId);
				Team homeTeam = daoTeam.getById(partido.getHomeTeamId());
				String homeTeamName = homeTeam.getShortName();
				String homeTeamCrest = homeTeam.getCrest();
				Team awayTeam = daoTeam.getById(partido.getAwayTeamId());
				String awayTeamName = awayTeam.getShortName();
				String awayTeamCrest = awayTeam.getCrest();
				Integer scoreHome = pronostico.getScoreHome();
				Integer scoreAway = pronostico.getScoreAway();

				Integer puntos = UpdatePronosticoController.calculatePoints(pronostico, partido);
				
				if(puntos != -1) {
					puntosTorneo += puntos;					
				}
				pronostico.setPuntos(puntos);
				dao.update(pronostico);
				mapPartidoPuntos.put(pronostico.getMatchID(), puntos);
				
				Partido p = new Partido(matchId ,homeTeamName, scoreHome, homeTeamCrest, awayTeamName, scoreAway, awayTeamCrest);
				partidos.add(p);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }

	    List<UserTorneo> userTorneoList = new ArrayList<>();
		try {
			userTorneoList = (List<UserTorneo>) daoUserTorneo.getByUserId(userId);
			for(UserTorneo userTorneo : userTorneoList) {
				if(puntosTorneo != userTorneo.getPuntos()) {
					userTorneo.setPuntos(puntosTorneo);
					daoUserTorneo.update(userTorneo);	    	
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    req.setAttribute("partidos", partidos);
	    req.setAttribute("mapPartidoPuntos", mapPartidoPuntos);
	    getServletContext().getRequestDispatcher("/mis-pronosticos.jsp").forward(req, resp);
	}
	
}
