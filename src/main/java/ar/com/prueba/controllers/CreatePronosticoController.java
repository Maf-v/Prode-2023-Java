package ar.com.prueba.controllers;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.com.prueba.dao.implement.MatchDAOMysqlImpl;
import ar.com.prueba.dao.implement.PronosticoDAOMysqlImpl;
import ar.com.deserialize.Match;
import ar.com.deserialize.Pronostico;
import ar.com.prueba.dao.iPronosticoDAO;
import ar.com.prueba.dao.iMatchDAO;


@WebServlet("/NewPronostico")
public class CreatePronosticoController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String matchId = req.getParameter("matchId");
		System.out.println("matchId : " + matchId);
		String scoreHome = req.getParameter("homeGoals");
		if(scoreHome == null) scoreHome = "0";
		System.out.println("scoreHome : " + scoreHome);
		String scoreAway = req.getParameter("awayGoals");
		if(scoreAway == null) scoreAway = "0";
		System.out.println("scoreAway : " + scoreAway);
		
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("uid");

		iMatchDAO daoMatch = new MatchDAOMysqlImpl();
		Match match = null;
		try {
			match = daoMatch.getById(Long.parseLong(matchId));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		iPronosticoDAO dao = new PronosticoDAOMysqlImpl();
		Pronostico pronostico = new Pronostico(userId, match.getID(), Integer.parseInt(scoreHome), Integer.parseInt(scoreAway));
		try {
			dao.create(pronostico);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
	}
	
}
