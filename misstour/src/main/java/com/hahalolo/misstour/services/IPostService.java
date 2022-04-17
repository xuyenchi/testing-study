package com.hahalolo.misstour.services;

import java.util.List;

import org.bson.types.ObjectId;

public interface IPostService {

	public boolean approval(String postId, String censorId, int status, int scope);
	public boolean unapproval(String postId, int status, String censorId, int scope, String sbd);
	public boolean passround(int beforeStatus, List<ObjectId> postIds, int afterStatus, String censorId);

}
