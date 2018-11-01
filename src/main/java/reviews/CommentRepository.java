package reviews;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
	
	
	Collection<Comment> findByReviewsContains(Review review);
	
	Collection<Comment> findByReviewsId(long id);

}
