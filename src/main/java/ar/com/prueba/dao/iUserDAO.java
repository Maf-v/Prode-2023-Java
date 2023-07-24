package ar.com.prueba.dao;

import ar.com.deserialize.User;

public interface iUserDAO {

	public void create(User newUser) throws Exception;
	
	public User getByUid(String uid) throws Exception;
}
