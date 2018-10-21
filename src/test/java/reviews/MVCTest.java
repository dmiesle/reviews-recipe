package reviews;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class MVCTest {
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private ReviewRepository reviewRepo;
	
	@Mock
	private Review review;
	
	@MockBean
	private CategoryRepository categoryRepo;
	
	@Mock
	private Category category;

	@Test
	public void shouldRouteToSingleReviewView() throws Exception {
		long reviewId = 1;
		when(reviewRepo.findById(reviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(view().name(is("review")));
	}
	@Test
	public void shouldBeOKForSingleReview() throws Exception{
		long reviewId = 1;
		when(reviewRepo.findById(reviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(status().isOk());
		
	}
	
	@Test
	public void shouldNavigateToAllReviewsView()throws Exception {
		mvc.perform(get("/reviews")).andExpect(view().name(is("reviews")));
	}
	
	@Test
	public void shouldRouteToSingleCategoryView() throws Exception {
		long categoryId = 1;
		when(categoryRepo.findById(categoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(view().name(is("category")));
	}
	
//	@Test
	public void shouldNavigateToAllCategoriesView() throws Exception {
		mvc.perform(get("/categories")).andExpect(view().name(is("categories")));
	}

}
