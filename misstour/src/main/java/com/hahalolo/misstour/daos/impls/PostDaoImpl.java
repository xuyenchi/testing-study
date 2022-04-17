package com.hahalolo.misstour.daos.impls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.hahalolo.misstour.daos.IPostDao;
import com.hahalolo.misstour.utils.MissTourConstants;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;

@Repository(value = MissTourConstants.POST_DAO_IMPL)
public class PostDaoImpl implements IPostDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public boolean approval(String postId, String censorId, int status, int scope, String sbd) {
		MongoCollection<Document> postCollection = mongoTemplate.getCollection("post");
		Date censortime = new Date();

//		BasicDBObject filters = new BasicDBObject();
//		filters.put("_id", new ObjectId(postId));
//		filters.put("status", 0);
		 
		BasicDBList filters = new BasicDBList();
		BasicDBObject filterById =  new BasicDBObject("_id", new ObjectId(postId));
		BasicDBObject filterByStatus = new BasicDBObject("status", 0);
		filters.add(filterById);
		filters.add(filterByStatus);
		

		BasicDBObject updates = new BasicDBObject();
		updates.put("status", 1);
		updates.put("censorId", censorId);
		updates.put("censortime", censortime);
		updates.put("sbd", sbd);
		updates.put("scope", scope);

		UpdateResult updateResult = postCollection.updateOne(new BasicDBObject("$and", filters), new BasicDBObject("$set", updates));
		if(updateResult.getModifiedCount() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean unapproval(String postId, int status, String censorId, int scope, String sbd) {
		MongoCollection<Document> postCollection = mongoTemplate.getCollection("post");
		Date censorTime = new Date();
		BasicDBObject filters = new BasicDBObject();
		filters.put("_id", new ObjectId(postId));
		
		BasicDBObject updatesSet = new BasicDBObject();
		updatesSet.put("status", status);
		updatesSet.put("censorid", censorId);
		updatesSet.put("scope", scope);
		updatesSet.put("censortime", censorTime);
		
		BasicDBObject updatesUnset = new BasicDBObject();
		updatesSet.put("sbd", "");
		
		BasicDBObject updates = new BasicDBObject();
		updates.put("$set", updatesSet);
		updates.put("$unset", updatesUnset);
		
		
		UpdateResult updateResult = postCollection.updateOne(filters, updates);
		if(updateResult.getModifiedCount()>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean passround(int beforeStatus, List<ObjectId> postIds, int afterStatus, String censorId) {
		MongoCollection<Document> postCollection = mongoTemplate.getCollection("post");
		Date censorTime = new Date();
		
		BasicDBObject filters = new BasicDBObject();
		filters.put("status", beforeStatus);
		filters.put("_id", new BasicDBObject("$in",postIds));
		
		BasicDBObject updates = new BasicDBObject();
		updates.put("status", afterStatus);
		updates.put("censortime", censorTime);
		updates.put("censorid", censorId);
		
		UpdateResult updateResult = postCollection.updateMany(filters, new BasicDBObject("$set", updates));
		if(updateResult.getModifiedCount() > 0) {
			return true;
		}
		return false;
	}

}
