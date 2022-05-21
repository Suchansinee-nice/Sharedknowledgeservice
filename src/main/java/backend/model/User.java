package backend.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

public class User {

	@Id
	String id;
	
	String type;
	String username;
	String password;
	String name;
	int sumXp;
	ArrayList<RankCategory> xp;
	ArrayList<Topic> posted;
	ArrayList<Topic> liked;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<Topic> getPosted() {
		return posted;
	}
	public void setPosted(ArrayList<Topic> posted) {
		this.posted = posted;
	}
	public ArrayList<Topic> getLiked() {
		return liked;
	}
	public void setLiked(ArrayList<Topic> liked) {
		this.liked = liked;
	}
	public String getId() {
		return id;
	}
	public int getSumXp() {
		return sumXp;
	}
	public void setSumXp(int sumXp) {
		this.sumXp = sumXp;
	}
	public ArrayList<RankCategory> getXp() {
		return xp;
	}
	public void setXp(ArrayList<RankCategory> xp) {
		this.xp = xp;
	}
	
	public void sumXP(){
		int sum = 0;
		List<RankCategory> tempList = getXp();
		for(RankCategory temp:tempList){
			sum += temp.getRank();
		}
		this.sumXp = sum;
	}
}
