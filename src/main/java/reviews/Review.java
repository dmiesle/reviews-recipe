package reviews;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@Entity
public class Review {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	
	@Lob
	private String description;
	
	@ManyToMany
	private Collection<Category> categories;
	private String imageUrl;
	
	public Review() {
		
	}

	public Review(String name, String imageUrl, String description, Category...categories) {
		this.name = name;
		this.description = description;
		this.categories = new HashSet<>(Arrays.asList(categories));
		this.imageUrl = imageUrl;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public String getImage() {
		return imageUrl;
	}
	

	public String getDescription() {
		return description;
	}
	
	public Collection<Category> getCategories(){
		return categories;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
