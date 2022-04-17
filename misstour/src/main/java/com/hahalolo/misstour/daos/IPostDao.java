package com.hahalolo.misstour.daos;

import java.util.List;

import org.bson.types.ObjectId;

public interface IPostDao {
	public boolean approval(String postId, String censorId, int status, int scope, String sbd);
	public boolean unapproval(String postId, int status, String censorId, int scope, String sbd);
	public boolean passround(int beforeStatus, List<ObjectId> postIds, int afterStatus, String censorId);

}
