package ar.com.prueba.controllers;

import ar.com.deserialize.Torneo;
import ar.com.prueba.dao.iTorneoDAO;
import ar.com.prueba.dao.implement.TorneoDAOMysqlImpl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateTorneo")

public class CreateTorneoController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("inputNombre");
		String uid = (String)req.getAttribute("uid");

		iTorneoDAO dao = new TorneoDAOMysqlImpl();
		Torneo torneo = new Torneo(nombre);
		
		try {
			dao.create(torneo);
			req.setAttribute("success", "Torneo creado");
			req.setAttribute("uid", uid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getServletContext().getRequestDispatcher("/ListaTorneos").forward(req, resp);
	}
}
