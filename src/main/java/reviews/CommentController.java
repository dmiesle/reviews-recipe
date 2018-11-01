package reviews;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CommentController {
	@Resource
	CommentRepository commentRepo;

	@RequestMapping("/comment")
	public String findOneComment(@RequestParam(value = "id") long id, Model model) throws CommentNotFoundException {
		Optional<Comment> comment = commentRepo.findById(id);

		if (comment.isPresent()) {
			model.addAttribute("comments", comment.get());
			return "comments";
		}
		throw new CommentNotFoundException();
	}

	@RequestMapping("/comments")
	public String findAllComments(Model model) {
		model.addAttribute("comments", commentRepo.findAll());
		return ("comments");

	}

}
