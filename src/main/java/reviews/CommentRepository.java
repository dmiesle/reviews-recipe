package reviews;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
	
	
	Collection<Comment> findByReviewContains(Review review);
	
	Collection<Comment> findByReviewId(long id);

}
