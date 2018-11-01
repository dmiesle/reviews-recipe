package reviews;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity
public class Comment {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	
	@Lob
	private String description;
	
	@OneToMany
	private Collection<Review> reviews;

	
	public Comment() {
		
	}

	public Comment (String name, String description) {
		this.name = name;
		this.description = description;

	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public Collection<Review> getReviews(){
		return reviews;
	}

}
