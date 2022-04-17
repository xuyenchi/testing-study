package com.hahalolo.misstour.services;

import java.util.List;


import org.bson.Document;

public interface IUserService {

	public List<Document> getUsers();
	public List<Document> getUsersByRole(int role);
	public String getName();
	public boolean isAdmin(String userId);
	public boolean updateUserRole(String userId, int role);
}
