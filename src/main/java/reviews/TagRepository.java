package reviews;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {
	
	
	Collection<Tag> findByReviewsContains(Review review);
	
	Collection<Tag> findByReviewsId(long id);

	Tag findByNameIgnoreCaseLike(String tagName);

	Collection<Review> findByTagContains(Tag tag);

}
