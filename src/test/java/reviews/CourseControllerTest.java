package reviews;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class CourseControllerTest {

	@InjectMocks
	private ReviewController underTest;
	
	@InjectMocks
	private CategoryController underTestCategory;

	@Mock
	private Review review;

	@Mock
	private Review review2;
	
	@Mock
	private ReviewRepository reviewRepo;
	
	@Mock
	private Category category;

	@Mock
	private Category category2;
	
	@Mock
	private CategoryRepository categoryRepo;

	@Mock
	private Model model;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldAddSingleReviewToModel() throws ReviewNotFoundException {
		long reviewId = 1;
		when(reviewRepo.findById(reviewId)).thenReturn(Optional.of(review));

		underTest.findOneReview(reviewId, model);
		verify(model).addAttribute("reviews", review);

	}
	@Test
	public void shouldAddAllReviewsToModel() {
		Collection<Review> allReviews = Arrays.asList(review, review2);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		
		underTest.findAllReviews(model);
		verify(model).addAttribute("reviews", allReviews);
	}
	
	@Test
	public void shouldAddSingleCategoryToModel() throws CategoryNotFoundException{
		long categoryId = 1;
		when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category));
		
		underTestCategory.findOneCategory(categoryId, model);
		verify(model).addAttribute("categories", category);
	}
	@Test
	public void shouldAddAllCategoriesToModel(){
		Collection<Category> allCategories = Arrays.asList(category, category2);
		when(categoryRepo.findAll()).thenReturn(allCategories);
		
		underTestCategory.findAllCategories(model);
		verify(model).addAttribute("categories", allCategories);
	}

}
