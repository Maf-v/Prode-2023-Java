package ar.com.prueba.controllers;

import ar.com.deserialize.UserTorneo;
import ar.com.prueba.dao.iUserTorneoDAO;
import ar.com.prueba.dao.implement.UserTorneoDAOMysqlImpl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CreateUserTorneo")
public class CreateUserTorneoController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String torneoId = req.getParameter("idTorneo");
		System.out.println(torneoId);
		
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("uid");
		
		iUserTorneoDAO dao = new UserTorneoDAOMysqlImpl();
		UserTorneo newUserTorneo = new UserTorneo(userId, Long.parseLong(torneoId));
		
		try {
			dao.create(newUserTorneo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getServletContext().getRequestDispatcher("/MisTorneos").forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
}
