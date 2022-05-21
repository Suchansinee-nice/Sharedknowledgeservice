package backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import backend.model.Topic;


public interface TopicRepo extends MongoRepository<Topic,String>{

}