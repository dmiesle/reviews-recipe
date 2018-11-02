package reviews;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Review {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String imageUrl;

	@Lob
	private String description;

	@ManyToOne
	private Category category;

	@ManyToMany
	private Collection<Tag> tags;

	@OneToMany(mappedBy = "review")
	private Collection<Comment> comments;

	public Review() {

	}

	public Review(String name, String imageUrl, String description, Category category, Tag... tags) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.imageUrl = imageUrl;
		this.tags = new HashSet<>(Arrays.asList(tags));
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

	public Category getCategory() {
		return category;
	}

	public Collection<Tag> getTags() {
		return tags;
	}

	public Collection<Comment> getComments() {
		return comments;
	}

	public void addTag(Tag newTag) {
		tags.add(newTag);

	}

	public void removeTag(Tag tagRemoveThis) {
		tags.remove(tagRemoveThis);
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
