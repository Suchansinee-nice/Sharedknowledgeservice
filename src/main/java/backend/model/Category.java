package backend.model;

import org.springframework.data.annotation.Id;

public class Category {

	@Id
	String id;
	
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}
}
