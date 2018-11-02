package reviews;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	
	Collection<Review> findByCategoryId(long id);

	Collection<Review> findByCategoryContains(Category category);




}
