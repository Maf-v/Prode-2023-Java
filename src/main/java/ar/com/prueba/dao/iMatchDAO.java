package ar.com.prueba.dao;

import ar.com.deserialize.Match;
import ar.com.deserialize.Partido;

public interface iMatchDAO {

	public void create(Match newMatch) throws Exception;
	
	public Partido getPartidoById(Long id) throws Exception;
	
	public Match getById(Long id) throws Exception;
}
