package reviews;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TagController {
	
	@Resource
	TagRepository tagRepo;

	@RequestMapping("/tag")
	public String findOneTag(@RequestParam(value = "id") long id, Model model) throws TagNotFoundException {
		Optional<Tag> tag = tagRepo.findById(id);

		if (tag.isPresent()) {
			model.addAttribute("tags", tag.get());
			return "tag";
		}
		throw new TagNotFoundException();
	}

	@RequestMapping("/tags")
	public String findAllTags(Model model) {
		model.addAttribute("tags", tagRepo.findAll());
		return ("tags");

	}

}