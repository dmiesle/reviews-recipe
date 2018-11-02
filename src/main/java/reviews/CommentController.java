package reviews;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommentController {
	@Resource
	private CommentRepository commentRepo;
	
	@Resource
	private ReviewRepository reviewRepo;


	@RequestMapping("/add-comment")
	public String addOneComment(Long reviewId, String description) {
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		Review review = reviewResult.get();
		
		Comment newComment = new Comment(description, review);
		commentRepo.save(newComment);
		return "redirect:/review?id=" + reviewId;
	}

}
