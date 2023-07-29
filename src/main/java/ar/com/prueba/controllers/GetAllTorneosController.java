package ar.com.prueba.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.com.deserialize.Torneo;
import ar.com.prueba.dao.iTorneoDAO;
import ar.com.prueba.dao.implement.TorneoDAOMysqlImpl;


@WebServlet("/ListaTorneos")
public class GetAllTorneosController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    iTorneoDAO dao = new TorneoDAOMysqlImpl();
	    List<Torneo> torneos = new ArrayList<>();
		
	    try {	    	
	    	torneos = dao.getAll();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("uid");
	    Set<Long> userTorneosIds = new HashSet<Long>();
	    try {
	    	List<Torneo> userTorneos = dao.getByUserId(userId);
			for(Torneo userTorneo : userTorneos) {
				userTorneosIds.add(userTorneo.getId());		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	    req.setAttribute("torneos", torneos); 
	    req.setAttribute("userTorneosIds", userTorneosIds);
	    getServletContext().getRequestDispatcher("/lista-torneos.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}
