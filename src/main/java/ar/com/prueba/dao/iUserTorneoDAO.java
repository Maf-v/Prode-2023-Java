package ar.com.prueba.dao;

import java.util.List;

import ar.com.deserialize.UserTorneo;

public interface iUserTorneoDAO {

	public void create(UserTorneo newUserTorneo) throws Exception;
	
	public List<UserTorneo> getUsersByTorneoId(Long torneoId) throws Exception;
}
