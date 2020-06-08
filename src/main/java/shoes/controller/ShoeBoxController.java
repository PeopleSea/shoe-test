package shoes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import shoes.model.ShoeBox;
import shoes.services.impl.ShoeBoxServiceImpl;

/**
 * @author eric.y
 * @param model
 * @return
 */
@Controller  
@RequestMapping(value = { "/shoeboxs" }, produces = { "application/json" })  
public class ShoeBoxController {

//	private static final Logger logger = LoggerFactory.getLogger(ShoeBoxController.class.getName());
	
	@Autowired
	private ShoeBoxServiceImpl shoeBoxService;
	
//	@Autowired
//    private RedisService redisService;
	
	@GetMapping("/all")
	public String queryShoeBoxAll(Model model) {
		List<ShoeBox> shoeBoxs = new ArrayList<>();
		shoeBoxs = this.shoeBoxService.queryAllBoxs();
		model.addAttribute("shoeBoxs", shoeBoxs);
//		logger.debug("shoeBoxService.queryAllBoxs(): " , shoeBoxService.queryAllBoxs());
		return "shoeBoxReport";
	}
}
