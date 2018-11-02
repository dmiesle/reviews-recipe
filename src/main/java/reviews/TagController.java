package reviews;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TagController {

	@Resource
	TagRepository tagRepo;

	@Resource
	ReviewRepository reviewRepo;
	
	@RequestMapping("/tag")
	public String findOneTag(@RequestParam(value = "id") long id, Model model) throws TagNotFoundException {
		Optional<Tag> tag = tagRepo.findById(id);

		if (tag.isPresent()) {
			model.addAttribute("tag", tag.get());
			return "tag";
		}
		throw new TagNotFoundException();

	}
	

	@RequestMapping("/add-tag")
	public String addTag(@RequestParam(value = "reviewid") long reviewid, String tagName) {
		Tag newTag = tagRepo.findByNameIgnoreCaseLike(tagName);

		if (newTag == null) {
			newTag = new Tag(tagName);
			tagRepo.save(newTag);

		}
		Optional<Review> reviewResult = reviewRepo.findById(reviewid);
		Review review = reviewResult.get();
		Tag existing = tagRepo.findByNameIgnoreCaseLike(tagName);
		if (!review.getTags().contains(existing)) {
			review.addTag(newTag);
			reviewRepo.save(review);
		}

		return "redirect:/review?id=" + reviewid;
	}

	@RequestMapping("/remove-tag-button")
	public String removeTagButton(@RequestParam Long tagId, @RequestParam long reviewid) {
		Optional<Tag> tagRemove = tagRepo.findById(tagId);
		Tag tagRemoveThis = tagRemove.get();

		Optional<Review> reviewResult = reviewRepo.findById(reviewid);
		Review review = reviewResult.get();

		review.removeTag(tagRemoveThis);
		reviewRepo.save(review);
		return "redirect:/review?id=" + reviewid;
	}

	@RequestMapping("/tags")
	public String findAllTags(Model model) {
		model.addAttribute("tags", tagRepo.findAll());
		return ("tags");

	}

	@RequestMapping(path = "/tags/{tagName}", method = RequestMethod.POST)
	public String addTag(@PathVariable String tagName, Model model) {
		Tag newTag = tagRepo.findByNameIgnoreCaseLike(tagName);

		if (newTag == null) {
			newTag = new Tag(tagName);
			tagRepo.save(newTag);

		}
		model.addAttribute("tagsModel", tagRepo.findAll());
		return "partials/tags-list-added";
	}
	
	@RequestMapping(path = "/tags/remove/{tagName}", method = RequestMethod.POST)
	public String removeTag(@PathVariable String tagName, Model model) {
		Tag tagToRemove = tagRepo.findByNameIgnoreCaseLike(tagName);
		if(tagRepo.findByNameIgnoreCaseLike(tagName) != null) {
			for(Review review: tagToRemove.getReviews()) {
				review.removeTag(tagToRemove);
				reviewRepo.save(review);
			}
		}
		tagRepo.delete(tagToRemove);
		model.addAttribute("tagsModel", tagRepo.findAll());
		return "partials/tags-list-removed";
	}
	

}