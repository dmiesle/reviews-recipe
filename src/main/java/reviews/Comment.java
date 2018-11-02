package reviews;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

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
	
	@Lob
	private String description;
	
	@OneToMany (mappedBy = "comment")
	private Collection<Review> reviews;

	
	public Comment() {
		
	}

	public Comment (String description, Review...reviews) {
		this.description = description;
		this.reviews = new HashSet<>(Arrays.asList(reviews));

	}

	public long getId() {
		return id;
	}


	public String getDescription() {
		return description;
	}
	
	public Collection<Review> getReviews(){
		return reviews;
	}

}
