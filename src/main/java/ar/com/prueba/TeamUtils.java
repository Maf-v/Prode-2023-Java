package ar.com.prueba;

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

import com.google.gson.Gson;

import ar.com.deserialize.DeserializeTeams;
import ar.com.deserialize.Team;
import ar.com.prueba.dao.iTeamDAO;
import ar.com.prueba.dao.implement.TeamDAOMysqlImpl;

@WebServlet("/createTeams")

public class TeamUtils extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpRequest request = null;
		try {
			request = HttpRequest.newBuilder()
					.uri(new URI("https://api.football-data.org/v4/competitions/CLI/teams"))
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
		DeserializeTeams deserialize = gson.fromJson(response.body(), DeserializeTeams.class);
		
		System.out.println(deserialize);
		System.out.println("ESTA BIEN");
		
		Team[] teams = deserialize.getTeams();
		System.out.println(teams);
		
		for(Team team : teams) {
			try {
				iTeamDAO dao = new TeamDAOMysqlImpl();
				dao.create(team);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Fallo al crearlo");
			}
		}
		
		
	}
	
}
