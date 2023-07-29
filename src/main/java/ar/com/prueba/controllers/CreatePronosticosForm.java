package ar.com.prueba.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.com.prueba.dao.implement.MatchDAOMysqlImpl;
import ar.com.prueba.dao.implement.PronosticoDAOMysqlImpl;
import ar.com.prueba.dao.implement.TeamDAOMysqlImpl;
import ar.com.prueba.dao.iMatchDAO;
import ar.com.prueba.dao.iTeamDAO;
import ar.com.deserialize.Match;
import ar.com.deserialize.Partido;
import ar.com.deserialize.Team;

@WebServlet("/CreatePronosticos")
public class CreatePronosticosForm extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String etapa = req.getParameter("etapa");
		
	    iTeamDAO daoTeam = new TeamDAOMysqlImpl();
		
		iMatchDAO dao = new MatchDAOMysqlImpl();
		List<Partido> partidosByStage = new ArrayList<Partido>();
		try {
			partidosByStage = dao.getPartidosByStage(etapa);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Partido> partidos = new ArrayList<Partido>();
		if(!partidosByStage.isEmpty()) {
			for(Partido partido : partidosByStage) {
				try {
					Long id = partido.getId();
					Team homeTeam = daoTeam.getById(partido.getHomeTeamId());
					String homeNameTeam = homeTeam.getShortName();
					String homeTeamCrest = homeTeam.getCrest();
					Team awayTeam = daoTeam.getById(partido.getAwayTeamId());
					String awayNameTeam = awayTeam.getShortName();
					String awayTeamCrest = awayTeam.getCrest();
					
					Partido newPartido = new Partido(id, homeNameTeam, homeTeamCrest, awayNameTeam, awayTeamCrest);
					partidos.add(newPartido);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
		// Devuelve un partido ya terminado para prueba
		if(etapa.equals("prueba")){
			try {
				Long id = Long.valueOf(433309);
				Team homeTeam = daoTeam.getById(Long.valueOf(7119));
				String homeNameTeam = homeTeam.getShortName();
				String homeTeamCrest = homeTeam.getCrest();
				Team awayTeam = daoTeam.getById(Long.valueOf(10137));
				String awayNameTeam = awayTeam.getShortName();
				String awayTeamCrest = awayTeam.getCrest();
				
				Partido newPartido = new Partido(id, homeNameTeam, homeTeamCrest, awayNameTeam, awayTeamCrest);
				partidos.add(newPartido);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		req.setAttribute("partidos", partidos);
		getServletContext().getRequestDispatcher("/crear-pronostico.jsp").forward(req, resp);
	}
	
}
