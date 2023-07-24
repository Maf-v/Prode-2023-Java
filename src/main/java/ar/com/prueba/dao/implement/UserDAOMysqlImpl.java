package ar.com.prueba.dao.implement;

import ar.com.deserialize.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import ar.com.prueba.dao.iTeamDAO;
import ar.com.prueba.dao.iUserDAO;
import ar.com.prueba.db.AdministradorConexion;
import ar.com.deserialize.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;


public class UserDAOMysqlImpl implements iUserDAO{
	@Override
	public void create(User newUser) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorConexion.getConnection();
		
		String sql = "insert into usuarios (uid, password) values (?,?)" ;
		PreparedStatement statement  = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		
		statement.setString(1, newUser.getUid());
		statement.setString(2, newUser.getPassword());
		
		 try {
			 statement.execute();			 
		 } catch (Exception e) {
			 e.printStackTrace();
			 System.out.println(e.getMessage());
		 }
		 
		 cerrar(connection);
	}
	
	@Override
	public User getByUid(String uid) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorConexion.getConnection();
		//2 - arma el statement
		String sql = "select * from usuarios where uid = '" + uid + "'";
		
		Statement statement  = connection.createStatement();
		
		//3 - obtengo el resulSet
		ResultSet resultset = statement.executeQuery(sql);
		
		if (resultset.next()) {
			String userId = resultset.getString("uid");
			String password = resultset.getString("password");
			return new User(userId,password);
		}
		cerrar(connection);
		System.out.println("fallo algo al obtener el usuario");
		return null;
	}
	
	
	
	private static void cerrar(Connection con) throws Exception{
		con.close();
	}
	
}
