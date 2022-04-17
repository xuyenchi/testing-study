package com.hahalolo.misstour.daos.impls;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.hahalolo.misstour.daos.IUserDao;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;

@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Document> getUsers() {
		MongoCollection<Document> userCollection = getCollection();
		FindIterable<Document> userIterable = userCollection.find();
		List<Document> listUser = new ArrayList<Document>();
		for (Document user : userIterable) {
			listUser.add(user);
		}
		return listUser;
	}

	@Override
	public boolean createUser(Document user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(String userId, Document user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Document> getUsersByRole(int role) {
		MongoCollection<Document> userCollection = getCollection();
		BasicDBObject filters = new BasicDBObject();
		filters.put("role", role);
		FindIterable<Document> userIterable = userCollection.find(filters);
		List<Document> listUsersByRole = new ArrayList<Document>();
		for (Document user : userIterable) {
			listUsersByRole.add(user);
		}
		return listUsersByRole;
	}

	@Override
	public Document getRole(String userId) {
		MongoCollection<Document> userCollection = getCollection();
		BasicDBObject filter = new BasicDBObject();
		filter.put("_id", new ObjectId(userId));
		filter.put("role", new BasicDBObject("$exists", true));

		BasicDBObject projectionFilter = new BasicDBObject();
		projectionFilter.put("role", 1);
		FindIterable<Document> userIterable = userCollection.find(filter).projection(projectionFilter);
		if (userIterable != null) {
			return userIterable.first();
		}

		return null;
	}

	@Override
	public boolean updateUserRole(String userId, int role) {
		MongoCollection<Document> userCollection = getCollection();
		BasicDBObject filters = new BasicDBObject();
		filters.put("_id", new ObjectId(userId));

		BasicDBObject update = new BasicDBObject();
		update.put("$set", new BasicDBObject("role", role));

		UpdateResult updateResult = userCollection.updateOne(filters, update);
		if (updateResult.getModifiedCount() > 0) {
			return true;
		}
		return false;
	}

	private MongoCollection<Document> getCollection() {
		MongoCollection<Document> userCollection = mongoTemplate.getCollection("user");
		return userCollection;
	}

}
