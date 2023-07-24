package ar.com.prueba.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import ar.com.deserialize.Deserialize;
import ar.com.deserialize.DeserializeTeams;
import ar.com.deserialize.Match;

import ar.com.prueba.dao.iMatchDAO;
import ar.com.prueba.dao.implement.MatchDAOMysqlImpl;


@WebServlet("/CreatePartidosCLI")
public class CreatePartidoController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpRequest request = null;
		try {
			request = HttpRequest.newBuilder()
					.uri(new URI("https://api.football-data.org/v4/competitions/CLI/matches?status=SCHEDULED"))
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

		iMatchDAO dao = new MatchDAOMysqlImpl();
		for(Match match : matches) {
			try {
				dao.create(match);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
		
	
}
