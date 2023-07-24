package ar.com.prueba.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.com.deserialize.Torneo;
import ar.com.prueba.dao.iTorneoDAO;
import ar.com.prueba.dao.implement.TorneoDAOMysqlImpl;

@WebServlet("/MisTorneos")
public class GetUserTorneos extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("uid");
		
		iTorneoDAO dao = new TorneoDAOMysqlImpl();
		List<Torneo> torneos = new ArrayList<>();
		try {
			torneos = dao.getByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		req.setAttribute("torneos", torneos);
		getServletContext().getRequestDispatcher("/mis-torneos.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
	
}
