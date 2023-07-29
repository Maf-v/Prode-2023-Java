package ar.com.prueba.dao;

import java.util.List;

import ar.com.deserialize.Match;
import ar.com.deserialize.Partido;

public interface iMatchDAO {

	public void create(Match newMatch) throws Exception;
	
	public Partido getPartidoById(Long id) throws Exception;
	
	public List<Partido> getPartidosByStage(String stage) throws Exception;
	
	public Match getById(Long id) throws Exception;
}
