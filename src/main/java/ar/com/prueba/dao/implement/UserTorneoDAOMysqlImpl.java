package ar.com.prueba.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
			 System.out.println(e.getMessage());
		 }
		 
		 cerrar(connection);
	}
	
	@Override
	public List<UserTorneo> getUsersByTorneoId(Long torneoId) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorConexion.getConnection();
		
		String sql = "SELECT * FROM `usuarios_torneo` WHERE torneoId = " + torneoId ;
		
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
	
	
	private static void cerrar(Connection con) throws Exception{
		con.close();
	}
}
