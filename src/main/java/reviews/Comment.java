package reviews;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	@Id
	@GeneratedValue
	private long id;

	@Lob
	private String description;

	@ManyToOne
	private Review review;

	public Comment() {

	}

	public Comment(String description, Review review) {
		this.description = description;
		this.review = review;

	}

	public long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Review getReview() {
		return review;
	}

}
