package com.hahalolo.misstour.rests;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hahalolo.misstour.services.IUserService;
import com.hahalolo.misstour.utils.MissTourConstants;

@RestController
@RequestMapping(value = "/user")
public class UserRest {

	@Autowired
	@Qualifier(value = MissTourConstants.UserServiceImplCurrent)
	IUserService userService;

	@GetMapping(value = "/list")
	public List<Document> getUsers() {
		return userService.getUsers();
	}
	
	@GetMapping(value = "/listbyrole")
	public List<Document> getUsersByRole(@RequestParam(required = false, name = "role") Integer role){
		 if(role != null) {
			 return userService.getUsersByRole(role);
		 }
		 return userService.getUsers();
	}
	@GetMapping(value = "/getname")
	public String getName() {
		return userService.getName();
	}
	
	@PostMapping(value = "/updateRole")
	public boolean updateUserRole(@RequestBody Document dataUser) {
		String censorId = dataUser.getString("censorId");
		if(userService.isAdmin(censorId)) {
			return userService.updateUserRole(dataUser.getString("userId"),
											  dataUser.getInteger("role"));
			
		}
		return false;
	}
	
}
