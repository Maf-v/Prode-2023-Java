package ar.com.prueba.dao;

import java.util.List;

import ar.com.deserialize.Team;

public interface iTeamDAO {
	
	public void create(Team newTeam) throws Exception;
	
	public Team getById(Long id) throws Exception;
	
}
