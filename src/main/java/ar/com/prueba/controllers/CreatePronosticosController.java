package ar.com.prueba.controllers;


import java.io.IOException;
import java.util.Map;

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


@WebServlet("/NewPronosticos")
public class CreatePronosticosController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    HttpSession session = req.getSession();
	    String userId = (String) session.getAttribute("uid");
	    
	    System.out.println("Llego aca");

	    iPronosticoDAO dao = new PronosticoDAOMysqlImpl();

	    // Obtener todos los parámetros del formulario
	    Map<String, String[]> parameterMap = req.getParameterMap();

	    // Iterar sobre los parámetros para procesar los datos de los partidos
	    for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
	    	System.out.println(entry);
	        String paramName = entry.getKey();
	        String[] paramValues = entry.getValue();

	        // Verificar que el parámetro es del tipo "matchIdX", "homeGoalsX" o "awayGoalsX"
	        if (paramName.startsWith("matchId") && paramValues.length > 0) {
	            String matchId = paramValues[0];
	            String scoreHome = req.getParameter("homeGoals" + matchId);
	            String scoreAway = req.getParameter("awayGoals" + matchId);

	            if (scoreHome == null || scoreHome.isEmpty()) {
	                scoreHome = "0";
	            }
	            if (scoreAway == null || scoreAway.isEmpty()) {
	                scoreAway = "0";
	            }

	            System.out.println("matchId : " + matchId);
	            System.out.println("scoreHome : " + scoreHome);
	            System.out.println("scoreAway : " + scoreAway);

	            Match match = null;
	            iMatchDAO daoMatch = new MatchDAOMysqlImpl();
	            try {
	                match = daoMatch.getById(Long.parseLong(matchId));
	            } catch (NumberFormatException e) {
	                e.printStackTrace();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }

	            Pronostico pronostico = new Pronostico(userId, match.getID(), Integer.parseInt(scoreHome),
	                    Integer.parseInt(scoreAway));
	            try {
	                dao.create(pronostico);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            
	        }
	    }

	    // Redirigir a la página de inicio (index.jsp) después de procesar los pronósticos
	    getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
	}
	
}