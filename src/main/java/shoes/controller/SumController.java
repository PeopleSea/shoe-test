package shoes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import shoes.model.Sum;
import shoes.services.impl.ShoeBoxServiceImpl;
import shoes.services.impl.ShoeFPServiceImpl;
import shoes.services.impl.ShoeServiceImpl;
import shoes.utils.GenerateShoeBoxNumberUtils;

@Controller
@RequestMapping(value = { "/sum" }, produces = { "application/json" })
public class SumController {
//private static final Logger logger = LoggerFactory.getLogger(ShoeFPController.class.getName());
	
	@Autowired
    private ShoeFPServiceImpl shoeFPService;
	
	@Autowired
    private ShoeBoxServiceImpl shoeBoxService;
	
	@Autowired
    private ShoeServiceImpl shoeService;

	@GetMapping("/all")
	public String queryShoeFPAll(Model model) {
		List<Sum> sums = new ArrayList<>();
		Sum sum = new Sum();
		int shoeNumber = this.shoeService.queryAllShoes().size();
		sum.setSumType("鞋子");
		sum.setPlanNum(800);
		sum.setProdNum(shoeNumber);
		sum.setAchiveRate(GenerateShoeBoxNumberUtils.percnet(Double.valueOf(shoeNumber), Double.valueOf(800)));
		sums.add(sum);
		int shoeBoxNumber = this.shoeBoxService.queryAllBoxs().size();
		sum = new Sum();
		sum.setSumType("鞋盒");
		sum.setPlanNum(1000);
		sum.setProdNum(shoeBoxNumber);
		sum.setAchiveRate(GenerateShoeBoxNumberUtils.percnet(Double.valueOf(shoeBoxNumber), Double.valueOf(1000)));
		sums.add(sum);
		int shoeFPNumber = this.shoeFPService.queryAllFP().size();
		sum = new Sum();
		sum.setSumType("成品");
		sum.setPlanNum(700);
		sum.setProdNum(shoeFPNumber);
		sum.setAchiveRate(GenerateShoeBoxNumberUtils.percnet(Double.valueOf(shoeFPNumber), Double.valueOf(700)));
		sums.add(sum);
		model.addAttribute("sums", sums);
		return "SumReport";
	}
	
	
}
