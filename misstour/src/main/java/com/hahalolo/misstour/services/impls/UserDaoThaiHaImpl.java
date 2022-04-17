package com.hahalolo.misstour.services.impls;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.hahalolo.misstour.daos.IUserDao;
import com.hahalolo.misstour.services.IUserService;
import com.hahalolo.misstour.utils.MissTourConstants;

@Service(value = MissTourConstants.UserServiceImplUserHa)
public class UserDaoThaiHaImpl implements IUserService{

	@Autowired
	IUserDao userDao;

	@Override
	public List<Document> getUsers() {

		return userDao.getUsers();
	}

	@Override
	public List<Document> getUsersByRole(int role) {
		if (role == 1 || role == 0) {
			return userDao.getUsersByRole(role);
		}
		return null;

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "User Dao Thai Ha";
	}

	@Override
	public boolean isAdmin(String userId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean updateUserRole(String userId, int role) {
		// TODO Auto-generated method stub
		return false;
	}

}
