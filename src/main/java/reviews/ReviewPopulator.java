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
	
	@Override
	public void run (String...args) throws Exception{
	
	Category appetizer = categoryRepo.save(new Category("Appetizer"));
	Category maindish = categoryRepo.save(new Category("Main Dish"));
	Category dessert = categoryRepo.save(new Category("Dessert"));
	Category side = categoryRepo.save(new Category("Side"));
	
	Review bruschetta = new Review("Bruschetta", "Traditional Italian food that involves hard starch and a topping", appetizer);
	bruschetta = reviewRepo.save(bruschetta);
	Review steak = new Review("Steak", "It's whats for dinner", maindish);
	steak = reviewRepo.save(steak);
	Review cookies = new Review("Cookies", "portable dessert delivery system", dessert);
	cookies = reviewRepo.save(cookies);
	Review fries = new Review("French Fries", "Potatos sliced into pieces and fried, even better when you double fry them", side);
	fries = reviewRepo.save(fries);
		
	}

}
