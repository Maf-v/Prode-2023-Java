package ar.com.prueba.dao;

import java.util.List;

import ar.com.deserialize.Torneo;

public interface iTorneoDAO {
	
	public void create(Torneo newTorneo) throws Exception;
	
	public List<Torneo> getAll() throws Exception;
	
	public List<Torneo> getByUserId(String userId) throws Exception;
	
	public Torneo getById(Long id) throws Exception;
	
	public List<Torneo> getByName(String name) throws Exception;

}
