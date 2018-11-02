package reviews;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReviewPopulator implements CommandLineRunner {

	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private CategoryRepository categoryRepo;
	
	@Resource
	private CommentRepository commentRepo;
	
	@Resource
	private TagRepository tagRepo;

	@Override
	public void run(String... args) throws Exception {

		Category appetizer = categoryRepo.save(new Category("Appetizer"));
		Category maindish = categoryRepo.save(new Category("Main Dish"));
		Category dessert = categoryRepo.save(new Category("Dessert"));
		Category side = categoryRepo.save(new Category("Side"));
		
		Tag glutenfree = tagRepo.save(new Tag("Gluten Free"));
		glutenfree = tagRepo.save(glutenfree);
		Tag vegetarian = tagRepo.save(new Tag("Vegetarian"));
		vegetarian = tagRepo.save(vegetarian);
		Tag kidfriendly = tagRepo.save(new Tag("Kid Friendly"));
		kidfriendly = tagRepo.save(kidfriendly);
		Tag meat = tagRepo.save(new Tag("Meat"));
		meat = tagRepo.save(meat);
		
		Review bruschetta = new Review("Bruschetta", "/img/bruschetta.jpg",
				"Traditional Italian food that involves hard starch and a topping", appetizer, vegetarian);
		bruschetta = reviewRepo.save(bruschetta);
		Review steak = new Review("Steak", "/img/steak.jpg", "It's whats for dinner", maindish, meat);
		steak = reviewRepo.save(steak);
		Review cookies = new Review("Breakfast Cookies", "/img/cookies.jpg",
				"These cookies have been coveted ever since their debut 35 years ago. It’s use of several cereals, coconut, and chocolate provide a crunchy, soft, chewy experience you normally only get one of in a cookie. The creator, Marial Miesle, is notorious for having these in the house at all times. It is rumored that she went a whole week eating only these and Diet Coke. While that may be an exaggeration, some members of her family are known to have exclusively eaten only these cookies for an entire day. Over the years the quality of ingredients has risen, as have the variations within the family. Unfortunately, for readers of this review, the recipe is a closely guarded secret only obtainable through marriage.",
				dessert, kidfriendly, vegetarian);
		cookies = reviewRepo.save(cookies);
		Review fries = new Review("French Fries", "/img/fries.jpg",
				"Potatos sliced into pieces and fried, even better when you double fry them", side, vegetarian, kidfriendly);
		fries = reviewRepo.save(fries);

		
		Comment one = commentRepo.save(new Comment("It's really that good", cookies));
		one = commentRepo.save(one);
		Comment two = commentRepo.save(new Comment("Needs more seasoning", steak));
		two = commentRepo.save(two);

	}

}
