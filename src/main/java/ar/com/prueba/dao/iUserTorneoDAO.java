package ar.com.prueba.dao;

import java.util.List;

import ar.com.deserialize.UserTorneo;

public interface iUserTorneoDAO {

	public void create(UserTorneo newUserTorneo) throws Exception;
	
	public void delete(Long userTorneoId) throws Exception;
	
	public void update(UserTorneo userTorneo) throws Exception;
	
	public List<UserTorneo> getByUserId(String userId) throws Exception;
	
	public UserTorneo getByUserAndTorneo(String userId, Integer torneoId) throws Exception;
	
	public List<UserTorneo> getUsersByTorneoId(Long torneoId) throws Exception;
}
