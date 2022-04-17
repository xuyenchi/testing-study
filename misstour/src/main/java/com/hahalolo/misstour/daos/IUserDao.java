package com.hahalolo.misstour.daos;

import java.util.List;

import org.bson.Document;

public interface IUserDao {

	public List<Document> getUsers();
	public boolean createUser(Document user);
	public boolean updateUser(String userId, Document user);
	public boolean updateUserRole(String userId, int role);
	public boolean deleteUser(String userId);
	public List<Document> getUsersByRole(int role);
	public Document getRole(String userId);
	
}
