package ar.com.prueba.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ar.com.deserialize.User;
import ar.com.deserialize.UserTorneo;
import ar.com.prueba.dao.iUserTorneoDAO;
import ar.com.prueba.db.AdministradorConexion;

public class UserTorneoDAOMysqlImpl implements iUserTorneoDAO {

	@Override
	public void create(UserTorneo newUserTorneo) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorConexion.getConnection();
		
		String sql = "insert into usuarios_torneo (userId, torneoId, puntos) values (?,?,?)" ;
		PreparedStatement statement  = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		
		statement.setString(1, newUserTorneo.getUserId());
		statement.setLong(2, newUserTorneo.getTorneoId());
		statement.setLong(3, 0);
		
		 try {
			 statement.execute();			 
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 
		 cerrar(connection);
	}
	
	@Override
	public void delete(Long userTorneoId) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorConexion.getConnection();
		//2 - arma el statement
		String sql = "DELETE FROM usuarios_torneo WHERE id = " + userTorneoId;
		Statement statement  = connection.createStatement();
		//3 -devuelve un entero devuelve 1 o 0, pero no hace falta confirmar para este caso 
		statement.executeUpdate(sql);
		cerrar(connection);
	}
	
	@Override
	public void update(UserTorneo userTorneo) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorConexion.getConnection();
		
		String sql = "UPDATE usuarios_torneo SET puntos = ? WHERE userId = ? AND torneoId = ? " ;
		PreparedStatement statement  = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		
		statement.setLong(1, userTorneo.getPuntos());
		statement.setString(2, userTorneo.getUserId());
		statement.setLong(3, userTorneo.getTorneoId());
		
		 try {
			 statement.execute();			 
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 
		 cerrar(connection);
	}
	
	@Override
	public List<UserTorneo> getUsersByTorneoId(Long torneoId) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorConexion.getConnection();
		
		String sql = "SELECT * FROM `usuarios_torneo` WHERE torneoId = " + torneoId + " ORDER BY puntos DESC";
		
		Statement statement  = connection.createStatement();
		
		ResultSet resultset = statement.executeQuery(sql);
		
		List<UserTorneo> userTorneoList = new ArrayList<>();
		while(resultset.next()) {
			String userId = resultset.getString("userId");
			Long puntos = resultset.getLong("puntos");
			UserTorneo userTorneo = new UserTorneo(userId, puntos, null);
			userTorneoList.add(userTorneo);
		}
		
		cerrar(connection);
		return userTorneoList;
	}
	
	@Override
	public List<UserTorneo> getByUserId(String userId) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorConexion.getConnection();
		
		String sql = "SELECT * FROM `usuarios_torneo` WHERE userId = '" + userId + "'";
		
		Statement statement  = connection.createStatement();
		
		ResultSet resultset = statement.executeQuery(sql);
		
		List<UserTorneo> listUserTorneo = new ArrayList<>();
		
		while(resultset.next()) {
			String userIdQuery = resultset.getString("userId");
			Long torneoId = resultset.getLong("torneoID");
			Long userTorneoId = resultset.getLong("id");
			Long puntos = resultset.getLong("puntos");
			UserTorneo newUserTorneo = new UserTorneo(userIdQuery,torneoId, puntos, userTorneoId);
			listUserTorneo.add(newUserTorneo);
		}
		
		cerrar(connection);
		return listUserTorneo;
	}
	
	@Override
	public UserTorneo getByUserAndTorneo(String userId, Integer torneoId) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorConexion.getConnection();
		
		String sql = "SELECT * FROM `usuarios_torneo` WHERE userId = '" + userId + "' AND torneoId = " + torneoId;
		
		Statement statement  = connection.createStatement();
		
		ResultSet resultset = statement.executeQuery(sql);
		
		if (resultset.next()) {
			String userIdQuery = resultset.getString("userId");
			Long torneoIdQuery = resultset.getLong("torneoId");
			Long userTorneoId = resultset.getLong("id");
			Long puntos = resultset.getLong("puntos");
			return new UserTorneo(userIdQuery,torneoIdQuery, puntos, userTorneoId);
		}
		
		cerrar(connection);
		return null;
	}
	
	
	private static void cerrar(Connection con) throws Exception{
		con.close();
	}
}
