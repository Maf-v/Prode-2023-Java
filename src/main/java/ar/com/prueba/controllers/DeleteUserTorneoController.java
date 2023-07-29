package ar.com.prueba.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.com.deserialize.UserTorneo;
import ar.com.prueba.dao.iUserTorneoDAO;
import ar.com.prueba.dao.implement.UserTorneoDAOMysqlImpl;

@WebServlet("/DeleteUserTorneo")
public class DeleteUserTorneoController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("uid");
		String torneoId = req.getParameter("idTorneoDelete");
		
		iUserTorneoDAO dao = new UserTorneoDAOMysqlImpl();
		UserTorneo userTorneo = null;
		try {
			userTorneo = dao.getByUserAndTorneo(userId, Integer.valueOf(torneoId));
			dao.delete(userTorneo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getServletContext().getRequestDispatcher("/MisTorneos").forward(req, resp);
	}
		
	
	
}
