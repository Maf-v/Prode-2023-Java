package ar.com.prueba.dao;

import java.util.List;

import ar.com.deserialize.Team;

public interface iTeamDAO {
	
	public void create(Team newTeam) throws Exception;
	
	public Team getById(Long id) throws Exception;
	
	/*
	public Team getByDni(Long dni) throws Exception; 
	
	// devuelve todos los registros de la tabla empleados
	public List<Team> findAll() throws Exception;
	
	// esto borra un registro por el dni del empleado
	public void delete(Long dni) throws Exception;

	public void update(Team empleado) throws Exception;
	
	//select * from departamentos where titulo like '%clave%' 
	public List<Team> search(String clave) throws Exception;
	*/
}
