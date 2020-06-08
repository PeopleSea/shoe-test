package shoes.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import shoes.model.Shoe;
import shoes.services.RedisService;
import shoes.services.impl.RedisServiceImpl;
import shoes.services.impl.ShoeServiceImpl;

@Controller
@RequestMapping(value = { "/shoes" }, produces = { "application/json" })
public class ShoeController {
	
	private static final Logger logger = LoggerFactory.getLogger(ShoeController.class.getName());
	
	@Autowired
    private ShoeServiceImpl shoeService;
	
	@Autowired
    private RedisServiceImpl redisService;


	@GetMapping("/all")
	public String queryShoeBoxAll(Model model) {
		List<Shoe> shoes = new ArrayList<>();
		shoes = this.shoeService.queryAllShoes();
		model.addAttribute("shoes", shoes);
		return "shoeReport";
	}
	
	
//	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Shoe queryShoeById(@PathVariable(value = "id") String id) {
		Shoe shoe = this.shoeService.getShoe(id);
		redisService.setObj(shoe.getId(), shoe.toString(), 60);
		return shoe;
//		return  this.shoeService.getShoe(id);
	}
}
