package reviews;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest

public class JPAMappingsTest {
	
	@Resource
	private TestEntityManager entityManager;
	
	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private CategoryRepository categoryRepo;
	
	@Resource
	private TagRepository tagRepo;
	
	@Resource
	private CommentRepository commentRepo;
	
	@Test
	public void shouldSaveAndLoadAReview() {
		Category category = categoryRepo.save(new Category("appetizer"));
		Review review = reviewRepo.save(new Review("review", "url", "description", category));
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getName(), is("review"));	
	}
	
	@Test
	public void shouldSaveAndLoadACategory() {
		Category category = categoryRepo.save(new Category("appetizer"));
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Category> result = categoryRepo.findById(categoryId);
		category = result.get();
		assertThat(category.getName(), is("appetizer"));	
	}
	
	@Test
	public void shouldEstablishCategoryToReviewsRelationship() {
		Category appetizer = categoryRepo.save(new Category("appetizer"));
		Tag snack = tagRepo.save(new Tag("snack"));
		
		entityManager.flush();
		entityManager.clear();
		
		Review review = new Review("Bruschetta", "url", "Traditional Italian food that involves hard starch and a topping", appetizer, snack);
		review = reviewRepo.save(review);
		
//		assertThat(review.getCategories(), contains(appetizer));
	}
	
	@Test
	public void shouleFindReviewsForCategories() {
		Category category = categoryRepo.save(new Category("appetizer"));
		
		Review bruschetta = new Review("Bruschetta","url", "Traditional Italian food that involves hard starch and a topping", category);
		bruschetta = reviewRepo.save(bruschetta);
		Review calamari = new Review("Fried Calamari","url", "Calamari lightly breaded and fried, served with dipping sauce", category);
		calamari = reviewRepo.save(calamari);
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> categoriesForReviews = reviewRepo.findByCategoryContains(category);
		assertThat(categoriesForReviews, containsInAnyOrder(bruschetta, calamari));
		
	}
	
	@Test
	public void shouldFindReviewsForCategoriesId() {
		Category appetizer = categoryRepo.save(new Category("appetizer"));
		Tag snack = tagRepo.save(new Tag("snack"));
		long categoryId = appetizer.getId();
		
		Review bruschetta = new Review("Bruschetta","url", "Traditional Italian food that involves hard starch and a topping", appetizer, snack);
		bruschetta = reviewRepo.save(bruschetta);
		Review calamari = new Review("Fried Calamari","url", "Calamari lightly breaded and fried, served with dipping sauce", appetizer);
		calamari = reviewRepo.save(calamari);
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> reviewsForCategory = reviewRepo.findByCategoryId(categoryId);
		assertThat(reviewsForCategory, containsInAnyOrder(bruschetta, calamari));
		
	}
	
	@Test
	public void shouldCreateATagAssociatedWithAreview() {
		Category appetizer = categoryRepo.save(new Category("appetizer"));
		Review bruschetta = new Review("Bruschetta","url", "Traditional Italian food that involves hard starch and a topping", appetizer);
		bruschetta = reviewRepo.save(bruschetta);
		
	
	}
	
	@Test
	public void shouldHaveTwoCommentsOnOneReview() {
		Category appetizer = new Category("Appetizer");
		Tag meat = new Tag("meat");
		appetizer = categoryRepo.save(appetizer);
		meat = tagRepo.save(meat);
		Review review = new Review("Bruschetta", "/img/bruschetta.jpg", "Traditional Italian food that involves hard starch and a topping", appetizer, meat);
		long reviewId = review.getId();
		
		Comment one = new Comment("comment", review);
		one = commentRepo.save(one);
		long oneId = one.getId();
		
		Comment two = new Comment("comment2", review);
		two = commentRepo.save(two);
		long twoId = two.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Iterable<Comment> commentOneResult = commentRepo.findAll();
		assertThat(commentOneResult, containsInAnyOrder(one, two));
		
		Optional<Comment> commentResult1 = commentRepo.findById(oneId);
		one = commentResult1.get();
		
		Optional<Comment> commentResult2 = commentRepo.findById(twoId);
		two = commentResult2.get();
		
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		review = reviewResult.get();
		
		assertThat(one.getDescription(), is("comment"));
		assertThat(two.getDescription(), is("comment2"));
	}

}
