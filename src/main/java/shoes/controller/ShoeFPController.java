package shoes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import shoes.model.ShoeFP;
import shoes.services.impl.ShoeFPServiceImpl;

@Controller
@RequestMapping(value = { "/shoefps" }, produces = { "application/json" })
public class ShoeFPController {
	
//	private static final Logger logger = LoggerFactory.getLogger(ShoeFPController.class.getName());
	
	@Autowired
    private ShoeFPServiceImpl shoeFPService;

	@GetMapping("/all")
	public String queryShoeFPAll(Model model) {
		List<ShoeFP> shoeFPs = new ArrayList<>();
		shoeFPs = this.shoeFPService.queryAllFP();
		model.addAttribute("shoeFPs", shoeFPs);
//		logger.debug("shoeService.queryAllShoes(): " , shoeService.queryAllShoes());
		return "shoeFPReport";
	}
}
