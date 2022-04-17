package com.hahalolo.misstour.rests;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hahalolo.misstour.services.IPostService;
import com.hahalolo.misstour.services.IUserService;
import com.hahalolo.misstour.utils.MissResponses;
import com.hahalolo.misstour.utils.MissTourConstants;
import com.hahalolo.misstour.utils.Status;

@RestController
@RequestMapping(value = "/post")
public class PostRest {
	@Autowired
	@Qualifier(value = MissTourConstants.POST_SERVICE_IMPL)
	private IPostService postService;
	
	@Autowired
	@Qualifier(value = MissTourConstants.UserServiceImplCurrent)
	private IUserService userService;
	
	@PostMapping(value = "/approval")
	public <E> MissResponses<E> approval(@RequestBody Document dataPost) {
		if(userService.isAdmin(dataPost.getString("censorId"))) {
			if( postService.approval(dataPost.getString("postId"), 
										dataPost.getString("censorId"), 
										dataPost.getInteger("status"), 
										dataPost.getInteger("scope"))) {
				return new MissResponses<E>(new Status(200,true, "duyet thanh cong"), null);
			}
			return new MissResponses<E>(new Status(2004,false, "khong thanh cong"), null);
			
		}else {
			return new MissResponses<E>(new Status(401,false, "khong co quyen"), null);
		}
		
	}
	
	@PostMapping(value = "/unapproval")
	public <E> MissResponses<E> unapproval(@RequestBody Document dataPost){
		if(userService.isAdmin(dataPost.getString("censorId"))) {
			if(postService.unapproval(dataPost.getString("postId"), 
										dataPost.getInteger("status"), 
										dataPost.getString("censorId"), 
										dataPost.getInteger("scope"),
										dataPost.getString("sdb"))){
				return new MissResponses<E>(new Status(200, true, "khong duyet thanh cong"), null);
			}
			return new MissResponses<E>(new Status(2004,false, "khong thanh cong"), null);
		} else {
			return new MissResponses<E>(new Status(401,false, "khong thanh cong"), null);
		}
	}
	
	@PostMapping(value = "/passround")
	public <E> MissResponses<E> passround(@RequestBody Document dataPost) {
		if(userService.isAdmin(dataPost.getString("censorId"))) {
			List<ObjectId> listPostIds = new ArrayList<ObjectId>();
			List<String> postIds = (List<String>)dataPost.get("postId");
			for(String postId : postIds) {
				listPostIds.add(new ObjectId(postId));
			}
			if(postService.passround(dataPost.getInteger("beforestatus"),
					listPostIds, 
					dataPost.getInteger("afterstatus"), 
					dataPost.getString("censorId"))){
				return new MissResponses<E>(new Status(200, true, "duyet dau vong thanh cong"),null);
			} else {
				return new MissResponses<E>(new Status(2004,false, "dau vong khong thanh cong"), null);
			}	
		}
		return new MissResponses<E>(new Status(401,false, "khong thanh cong"), null);

	}
	
	

}
