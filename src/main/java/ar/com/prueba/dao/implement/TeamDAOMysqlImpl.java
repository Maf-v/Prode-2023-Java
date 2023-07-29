package ar.com.prueba.dao.implement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import ar.com.prueba.dao.iTeamDAO;
import ar.com.prueba.db.AdministradorConexion;
import ar.com.deserialize.Match;
import ar.com.deserialize.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class TeamDAOMysqlImpl implements iTeamDAO{
	@Override
	public void create(Team newTeam) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorConexion.getConnection();
		
		 String sql = "insert into equipos (id, nombre, abreviatura, liga, img_logo) values (?,?,?,?,?)" ;
		 PreparedStatement statement  = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		 
		 statement.setFloat(1, newTeam.getID());
		 statement.setString(2, newTeam.getShortName());
		 statement.setString(3, newTeam.getTLA());
		 statement.setString(4, "CLI");
		 statement.setString(5, newTeam.getCrest());
		 
		 try {
			 statement.execute();			 
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 
		 cerrar(connection);
	}
	
	@Override
	public Team getById(Long id) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorConexion.getConnection();
		
		String sql = "SELECT * FROM `equipos` WHERE id = '" + id + "'" ;
		
		Statement statement  = connection.createStatement();
		
		ResultSet resultset = statement.executeQuery(sql);
		
		Team team = null;
		if(resultset.next()) {
			Long teamId = resultset.getLong("id");
			String shortName = resultset.getString("nombre");
			String tla = resultset.getString("abreviatura");
			String crest = resultset.getString("img_logo");
			team = new Team(teamId, shortName, tla, crest);
		}
		
		cerrar(connection);	
		return team;
	}
	
	
	private static void cerrar(Connection con) throws Exception{
		con.close();
	}
}
