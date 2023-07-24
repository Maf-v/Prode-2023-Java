package ar.com.prueba;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import ar.com.deserialize.Deserialize;
import ar.com.deserialize.Match;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CrearPronosticos")

public class prueba extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpRequest request = null;
		try {
			request = HttpRequest.newBuilder()
					.uri(new URI("https://api.football-data.org/v4/competitions/CLI/matches?matchday=7"))
					.setHeader("X-Auth-Token", "2cf95359348b495f8b8c5cf51b951024")
					.build();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		Deserialize deserialize = gson.fromJson(response.body(), Deserialize.class);
		
		Match[] matches = deserialize.getMatches();
		
		req.setAttribute("weight", 35);
		
		req.setAttribute("matches", matches);
		getServletContext().getRequestDispatcher("/crear-pronostico.jsp").forward(req, resp);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
