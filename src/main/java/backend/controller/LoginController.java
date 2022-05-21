package backend.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import backend.model.LoginFrom;
import backend.model.User;
import backend.repository.UserRepo;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	private UserRepo userRepo;
	
	@RequestMapping(method=RequestMethod.POST)
	public LoginFrom login(@RequestBody User user){
	LoginFrom login = new LoginFrom();
	login.setAccess(false);
	login.setId(null);
	List<User> list = userRepo.findAll();
		for(User temp : list){
			if(user.getUsername().equals(temp.getUsername())){
				if(user.getPassword().equals(temp.getPassword())){
					login.setAccess(true);
					login.setId(temp.getId());
					return login;
				}else{
					return login;
				}
			}else{
				continue;
			}
		}
		return login;
	}
}
