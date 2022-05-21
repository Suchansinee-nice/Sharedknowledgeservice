package backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import backend.model.User;

public interface UserRepo extends MongoRepository<User,String>{

}
