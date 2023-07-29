package ar.com.prueba.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ar.com.deserialize.Pronostico;
import ar.com.deserialize.Torneo;
import ar.com.prueba.dao.iPronosticoDAO;
import ar.com.prueba.db.AdministradorConexion;

public class PronosticoDAOMysqlImpl implements iPronosticoDAO {

	@Override
	public void create(Pronostico newPronostico) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorConexion.getConnection();
		
		String sql = "insert into pronosticos (userId, matchId, scoreHome, scoreAway) values (?,?,?,?)" ;
		PreparedStatement statement  = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		
		statement.setString(1, newPronostico.getUserId());
		statement.setLong(2, newPronostico.getMatchID());
		statement.setInt(3, newPronostico.getScoreHome());
		statement.setInt(4, newPronostico.getScoreAway());
		
		 try {
			 statement.execute();			 
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 
		 cerrar(connection);
	}
	
	@Override
	public List<Pronostico> getByUserId(String userId) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorConexion.getConnection();
		
		String sql = "SELECT * FROM `pronosticos` WHERE userId = '"+ userId +"'";
		
		Statement statement  = connection.createStatement();
		
		ResultSet resultset = statement.executeQuery(sql);

		List<Pronostico> pronosticos = new ArrayList<Pronostico>();
		while(resultset.next()) {
			Long matchId = resultset.getLong("matchId");
			Integer scoreHome = resultset.getInt("scoreHome");
			Integer scoreAway = resultset.getInt("scoreAway");
			Pronostico pronostico = new Pronostico(userId, matchId, scoreHome, scoreAway);
			pronosticos.add(pronostico);
		}
		
		cerrar(connection);
		return pronosticos;
	}
	
	@Override
	public void update(Pronostico pronostico) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorConexion.getConnection();
		
		String sql = "UPDATE pronosticos SET puntos = ? WHERE userId = ? AND matchId = ? " ;
		PreparedStatement statement  = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		
		statement.setInt(1, pronostico.getPuntos());
		statement.setString(2, pronostico.getUserId());
		statement.setLong(3, pronostico.getMatchID());
		
		 try {
			 statement.execute();			 
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 
		 cerrar(connection);
	}
	
	private static void cerrar(Connection con) throws Exception{
		con.close();
	}
}
