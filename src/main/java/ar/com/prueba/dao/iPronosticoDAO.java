package ar.com.prueba.dao;

import java.util.List;

import ar.com.deserialize.Pronostico;

public interface iPronosticoDAO {
	
	public void create(Pronostico newPronostico) throws Exception;
	
	public List<Pronostico> getByUserId(String userId) throws Exception;
	
	public void update(Pronostico pronostico) throws Exception;

}
