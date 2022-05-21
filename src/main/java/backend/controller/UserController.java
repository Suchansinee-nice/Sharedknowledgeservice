package backend.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import backend.model.Category;
import backend.model.RankCategory;
import backend.model.Topic;
import backend.model.User;
import backend.repository.UserRepo;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private TopicController topicController;
	@Autowired
	private CategoryController cateController;
	
	@RequestMapping(value ="/create",method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody User user){
		userRepo.save(user);
		return user;
	}
	
	@RequestMapping(value = "/getalluser",method=RequestMethod.GET)
	public List<User> getAll(){
		List<User> list = userRepo.findAll();
		return list;
	}
	
	@RequestMapping(value = "/delete/{id}",method=RequestMethod.DELETE)
	public void delete(@PathVariable String id){
		userRepo.delete(userRepo.findOne(id));
		//userRepo.delete(id);
	}
	
	@RequestMapping(value = "/share/{id}",method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public String share(@PathVariable String id,@RequestBody Topic topic){
		User user = userRepo.findOne(id);
		topic.setPoster(user.getName());
		topicController.create(topic);
		Topic temp = topicController.getById(topic.getId());
		if(user.getPosted()!=null){
			user.getPosted().add(temp);
		}else{
			ArrayList<Topic> newList = new ArrayList<Topic>();
			newList.add(temp);
			user.setPosted(newList);
		}
		boolean checkName = false;
		int rankIndex = 0;
		if(user.getXp()!=null){
			for(int i = 0;i<user.getXp().size();i++){
				if(user.getXp().get(i).getName().equals(topic.getCategory())){
					checkName = true;
					rankIndex = i;
					break;
				}
			}
			if(checkName==true){
				RankCategory tempRank = user.getXp().get(rankIndex);
				tempRank.setRank(tempRank.getRank()+1);
				user.getXp().set(rankIndex,tempRank);
			}else{
				RankCategory tempRank = new RankCategory();
				tempRank.setRank(1);
				tempRank.setName(topic.getCategory());
				user.getXp().add(tempRank);
				Category cate = new Category();
				cate.setName(topic.getCategory());
				cateController.create(cate);
			}
		}else{
			ArrayList<RankCategory> newRank = new ArrayList<RankCategory>();
			RankCategory tempRank = new RankCategory();
			tempRank.setName(topic.getCategory());
			tempRank.setRank(1);
			newRank.add(tempRank);
			user.setXp(newRank);
			Category cate = new Category();
			cate.setName(topic.getCategory());
			cateController.create(cate);
		}
		user.sumXP();
		userRepo.save(user);
		return "share success";
	}
}
