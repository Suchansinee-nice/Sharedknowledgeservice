package backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import backend.model.Category;

public interface CategoryRepo extends MongoRepository<Category,String>{

}
