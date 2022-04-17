package com.hahalolo.misstour.services.impls;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.hahalolo.misstour.daos.IUserDao;
import com.hahalolo.misstour.services.IUserService;
import com.hahalolo.misstour.utils.MissTourConstants;

@Service(value = MissTourConstants.UserServiceImplUserXuyen)
public class UserServiceImpl implements IUserService {

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
		return "User Xuyen";
	}

	@Override
	public boolean isAdmin(String userId) {
		
		Document user = userDao.getRole(userId);
		
		if(user != null && user.containsKey("role")) {
			int role = user.getInteger("role").intValue();
			if(role == 1) {
				return true;
			}
		}
		// user null, k ton tai role, role # 1
		return false;
	}

	@Override
	public boolean updateUserRole(String userId, int role) {
		if(role == 0 || role == 1) {
		return userDao.updateUserRole(userId, role);
		}
		return false;
	}

}
