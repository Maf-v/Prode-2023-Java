package ar.com.prueba.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import ar.com.deserialize.Torneo;
import ar.com.prueba.dao.iTorneoDAO;
import ar.com.prueba.db.AdministradorConexion;

public class TorneoDAOMysqlImpl implements iTorneoDAO {
	
	@Override
	public void create(Torneo newTorneo) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorConexion.getConnection();
		
		String sql = "insert into torneos (nombre) values (?)" ;
		PreparedStatement statement  = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		
		statement.setString(1, newTorneo.getNombre());
		
		 try {
			 statement.execute();			 
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 
		 cerrar(connection);
	}
	
	@Override
	public Torneo getById(Long id) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorConexion.getConnection();
		
		String sql = "SELECT * FROM `torneos` WHERE id = " + id ;
		
		Statement statement  = connection.createStatement();
		
		ResultSet resultset = statement.executeQuery(sql);
		
		Torneo torneo = null;
		if(resultset.next()) {
			Long torneoId = resultset.getLong("id");
			String nombre = resultset.getString("nombre");
			torneo = new Torneo(torneoId, nombre);
		}
		
		cerrar(connection);	
		return torneo;
	}

	@Override
	public List<Torneo> getAll() throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorConexion.getConnection();
		//2 - arma el statement
	    String sql = "select * from torneos";
			   
		Statement statement  = connection.createStatement();
				
		//3 - obtengo el resulSet
		ResultSet resultset = statement.executeQuery(sql);
		
		List<Torneo> torneos = new ArrayList<Torneo>();
		
		while(resultset.next()) {
			Long id = resultset.getLong("id");
			String nombre = resultset.getString("nombre");
			Torneo torneo = new Torneo(id, nombre);
			torneos.add(torneo);
		}
		
		cerrar(connection);	
		return torneos; //
	}
	
	@Override
	public List<Torneo> getByUserId(String userId) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorConexion.getConnection();
		
		String sql = "SELECT * FROM `torneos` WHERE id IN (SELECT torneoId FROM `usuarios_torneo` WHERE userId = '"+ userId +"')";
		
		Statement statement  = connection.createStatement();
		
		ResultSet resultset = statement.executeQuery(sql);

		List<Torneo> torneos = new ArrayList<Torneo>();
		while(resultset.next()) {
			Long id = resultset.getLong("id");
			String nombre = resultset.getString("nombre");
			Torneo torneo = new Torneo(id,nombre);
			torneos.add(torneo);
		}
		
		cerrar(connection);
		return torneos;
	}
	
	@Override
	public List<Torneo> getByName(String name) throws Exception {
		//-1 necesito la conection a la base
		Connection connection = AdministradorConexion.getConnection();
		
		String sql = "SELECT * FROM `torneos` WHERE nombre LIKE '%"+ name +"%'";
		
		Statement statement  = connection.createStatement();
		
		ResultSet resultset = statement.executeQuery(sql);

		List<Torneo> torneos = new ArrayList<Torneo>();
		while(resultset.next()) {
			Long id = resultset.getLong("id");
			String nombre = resultset.getString("nombre");
			Torneo torneo = new Torneo(id,nombre);
			torneos.add(torneo);
		}
		
		cerrar(connection);
		return torneos;
	}
	
	
	private static void cerrar(Connection con) throws Exception{
		con.close();
	}
}
