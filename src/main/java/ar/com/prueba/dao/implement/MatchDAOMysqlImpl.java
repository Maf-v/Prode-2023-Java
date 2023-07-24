package ar.com.prueba.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import ar.com.deserialize.Match;
import ar.com.deserialize.Partido;
import ar.com.prueba.dao.iMatchDAO;
import ar.com.prueba.db.AdministradorConexion;


public class MatchDAOMysqlImpl implements iMatchDAO{

	@Override
	public void create(Match newMatch) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorConexion.getConnection();
		
		String sql = "insert into partidos (id, status, stage, homeTeamId, awayTeamId, scoreHome, scoreAway) values (?,?,?,?,?,?,?)" ;
		PreparedStatement statement  = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		
		statement.setLong(1, newMatch.getID());
		statement.setString(2, newMatch.getStatus());
		statement.setString(3, newMatch.getStage());
		statement.setLong(4, newMatch.getHomeTeam().getID());
		statement.setLong(5, newMatch.getAwayTeam().getID());
		statement.setObject(6, newMatch.getScore().getFullTime().getHome());
		statement.setObject(7, newMatch.getScore().getFullTime().getAway());
		
		 try {
			 statement.execute();			 
		 } catch (Exception e) {
			 e.printStackTrace();
			 System.out.println(e.getMessage());
		 }
		 
		 cerrar(connection);
	}
	
	@Override
	public Partido getPartidoById(Long id) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorConexion.getConnection();
		
		String sql = "SELECT * FROM `partidos` WHERE id = '" + id + "'" ;
		
		Statement statement  = connection.createStatement();
		
		ResultSet resultset = statement.executeQuery(sql);
		
		Partido partido = null;
		if(resultset.next()) {
			Long matchId = resultset.getLong("id");
			Long homeTeamId = resultset.getLong("homeTeamId");
			Long awayTeamId = resultset.getLong("awayTeamId");
			partido = new Partido(matchId, homeTeamId, awayTeamId);
		}
		
		cerrar(connection);	
		return partido;
	}
	
	@Override
	public Match getById(Long id) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorConexion.getConnection();
		
		String sql = "SELECT * FROM `partidos` WHERE id = '" + id + "'" ;
		
		Statement statement  = connection.createStatement();
		
		ResultSet resultset = statement.executeQuery(sql);
		
		Match match = null;
		if(resultset.next()) {
			Long matchId = resultset.getLong("id");
			Long homeTeamId = resultset.getLong("homeTeamId");
			Long awayTeamId = resultset.getLong("awayTeamId");
			match = new Match(matchId);
		}
		
		cerrar(connection);	
		return match;
	}
	
	private static void cerrar(Connection con) throws Exception{
		con.close();
	}
}
