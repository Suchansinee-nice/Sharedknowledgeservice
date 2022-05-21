package backend.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import backend.model.Topic;
import backend.model.User;
import backend.repository.TopicRepo;
import backend.repository.UserRepo;

@RestController
@RequestMapping("/api/topic")
public class TopicController {

	@Autowired
	TopicRepo topicRepo;
	@Autowired
	UserRepo userRepo;
	
	@RequestMapping(value = "/get-by-category/{cate}",method=RequestMethod.GET)
	public List<Topic> getTopicByCate(@PathVariable String cate){
		List<Topic> allTopic = topicRepo.findAll();
		List<Topic> filterTopic = new ArrayList<Topic>();
		for(Topic temp : allTopic){
			if((temp.getCategory().toUpperCase()).equals(cate.toUpperCase())){
				filterTopic.add(temp);
			}
		}
		return filterTopic;
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Topic create(@RequestBody Topic topic){
		Date date = new Date();
		topic.setCreateDate(date);
		topicRepo.save(topic);
		return topic;
	}
	
	@RequestMapping(value="/getalltopic",method=RequestMethod.GET)
	public List<Topic> getAll(){
		List<Topic> list = topicRepo.findAll();
		return list;
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable String id){
		topicRepo.delete(id);
		List<User> list = userRepo.findAll();
		for(User temp:list){
			for(Topic tempp:temp.getPosted()){
				if(tempp.getId().equals(id)){
					list.remove(tempp);
				}	
			}
		}
		return "item : " + id + "is deleted!";
	}
	
	@RequestMapping(value="get-by-id/{id}",method=RequestMethod.GET)
	public Topic getById(@PathVariable String id){
		Topic topic = topicRepo.findOne(id);
		return topic;
	}
	
	
	
}
