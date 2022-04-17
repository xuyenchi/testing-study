package com.hahalolo.misstour.services.impls;

import java.security.SecureRandom;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hahalolo.misstour.daos.IPostDao;
import com.hahalolo.misstour.services.IPostService;
import com.hahalolo.misstour.utils.MissTourConstants;

@Service(value = MissTourConstants.POST_SERVICE_IMPL)
public class PostServiceImpl implements IPostService {

	@Autowired
	private IPostDao postDao;

	@Override
	public boolean approval(String postId, String censorId, int status, int scope) {
		String sbd = String.valueOf(new SecureRandom().nextInt(1000));
		return postDao.approval(postId, censorId, status, scope, sbd);

	}

	@Override
	public boolean unapproval(String postId, int status, String censorId, int scope, String sbd) {
		return postDao.unapproval(postId, status, censorId, scope, sbd);
	}

	@Override
	public boolean passround(int beforeStatus, List<ObjectId> postIds, int afterStatus, String censorId) {
		return postDao.passround(beforeStatus, postIds, afterStatus, censorId);
	}

	

}
