package ar.com.prueba.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ar.com.deserialize.Match;
import ar.com.deserialize.Partido;
import ar.com.deserialize.Pronostico;
import ar.com.deserialize.Team;
import ar.com.prueba.dao.iTeamDAO;
import ar.com.prueba.dao.iPronosticoDAO;
import ar.com.prueba.dao.iMatchDAO;
import ar.com.prueba.dao.implement.MatchDAOMysqlImpl;
import ar.com.prueba.dao.implement.PronosticoDAOMysqlImpl;
import ar.com.prueba.dao.implement.TeamDAOMysqlImpl;

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
	    
	    try {
			pronosticos = dao.getByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    List<Partido> partidos = new ArrayList<>();
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
				Partido p = new Partido(matchId ,homeTeamName, scoreHome, homeTeamCrest, awayTeamName, scoreAway, awayTeamCrest);
				partidos.add(p);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }

	    req.setAttribute("partidos", partidos);
	    getServletContext().getRequestDispatcher("/mis-pronosticos.jsp").forward(req, resp);
	}
	
}
