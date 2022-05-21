package backend.controller;

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
import backend.repository.CategoryRepo;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryRepo repo;
	
	@RequestMapping(value = "/create",method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Category create(@RequestBody Category category){
		repo.save(category);
		return category;
	}
	
	@RequestMapping(value="/delete/{type}",method=RequestMethod.DELETE)
	public void delete(@PathVariable String type){
		List<Category> list = repo.findAll();
		for(Category temp:list){
			if(temp.getName().equals(type)){
				repo.delete(temp);
			}
		}
	}
	
	@RequestMapping(value="/getallcategory",method=RequestMethod.GET)
	public List<Category> getAllCategory() {
		List<Category> listCategory = repo.findAll();
		return listCategory;
	}
	
	@RequestMapping(value="/get/{id}" , method=RequestMethod.GET)
	public Category getCategoryById(@PathVariable String id) {
		Category category = repo.findOne(id);
		return category;
	}
	
}
