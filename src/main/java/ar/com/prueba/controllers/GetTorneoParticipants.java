package ar.com.prueba.controllers;

import ar.com.prueba.dao.iTorneoDAO;
import ar.com.prueba.dao.iUserTorneoDAO;
import ar.com.prueba.dao.implement.TorneoDAOMysqlImpl;
import ar.com.prueba.dao.implement.UserTorneoDAOMysqlImpl;
import ar.com.deserialize.Torneo;
import ar.com.deserialize.UserTorneo;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Torneo")
public class GetTorneoParticipants extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String torneoId = req.getParameter("idTorneo");
		
		iUserTorneoDAO dao = new UserTorneoDAOMysqlImpl();
		List<UserTorneo> listUserTorneo = new ArrayList<>();
		try {
			listUserTorneo = dao.getUsersByTorneoId(Long.parseLong(torneoId));
			req.setAttribute("users", listUserTorneo);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		iTorneoDAO torneoDAO = new TorneoDAOMysqlImpl();
		Torneo torneo = null;
		try {
			torneo = torneoDAO.getById(Long.parseLong(torneoId));
			req.setAttribute("torneo", torneo);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getServletContext().getRequestDispatcher("/torneo.jsp").forward(req, resp);
	}
	
}
