package shoes;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import shoes.model.CreateShoe;
import shoes.model.Invoker;
import shoes.model.Shoe;
import shoes.model.ShoeBox;
import shoes.model.ShoeCommand;
import shoes.model.ShoeFP;
import shoes.model.facade.Facade;
import shoes.services.impl.ShoeBoxServiceImpl;
import shoes.services.impl.ShoeFPServiceImpl;
import shoes.services.impl.ShoeServiceImpl;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class ShoesTestApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(ShoesTestApplicationTests.class);
	
	@Autowired
	private ShoeServiceImpl shoeService;
	
	@Autowired
	private ShoeBoxServiceImpl shoeBoxService;
	
	@Autowired
	private ShoeFPServiceImpl shoeFPService;
	
//	@Test
	void contextLoads() {
	}

	@Test
	void testSingleton() {
		Shoe shoe = Shoe.returnShoe();
//		System.out.println("Shoe Singleton" + shoe.toString());
		ShoeBox shoeBox = ShoeBox.returnShoeBox();
//		System.out.println("shoeBox Singleton" + shoeBox.toString());
		ShoeFP shoeFP = ShoeFP.returnShoeFP();
		shoeFP.setShoe(shoe);
		shoeFP.setShoeBox(shoeBox);
//		System.out.println("shoeFP Singleton" + shoeFP.toString());
	}
	
//	@Test
	void testCreateShoeFP() {
		Shoe shoe = Shoe.returnShoe();
		shoe = shoeService.createShoe(shoe);
		System.out.println("Shoe Singleton" + shoe.toString());
		ShoeBox shoeBox = ShoeBox.returnShoeBox();
		shoeBox = shoeBoxService.createShoeBox(shoeBox);
		System.out.println("shoeBox Singleton" + shoeBox.toString());
		String combinNumber = shoeBox.getShoeBoxNumber() + shoe.getShoeNumber();
		String tempFPNumber= combinNumber.substring(combinNumber.length()/2-6, combinNumber.length()/2+6);
		StringBuffer sb = new StringBuffer(tempFPNumber);
		sb.insert(tempFPNumber.length()/2, "-");
		ShoeFP shoeFP = ShoeFP.returnShoeFP();
		shoeFP.setShoe(shoe);
		shoeFP.setShoeFpName(shoe.getShoeName()+shoeBox.getShoeBoxName());
		shoeFP.setShoeFpNumber(sb.toString());
		shoeFP.setShoeBox(shoeBox);
		shoeFP = shoeFPService.createShoeFP(shoeFP);
		System.out.println("shoeFP Singleton" + shoeFP.toString());
	}
	
//	@Test
	void testFacade() {
		List<ShoeFP> shoeFPs = Facade.createShoeFP(Facade.createShoe(1), Facade.createBox(1), 1);
		System.out.println(shoeFPs.toString());
	}
	
//	@Test
	void testCommand() {
		Shoe shoe = new Shoe();
        ShoeCommand createShoe = new CreateShoe(shoe);
        Invoker invoker = new Invoker();
        invoker.addCommand(createShoe);
        invoker.execute();
	}
	
	
	@Test
	void finalRun() {
		for(int i=0;i<100;i++) {
			ShoeBox shoeBox = ShoeBox.returnShoeBox();
			shoeBoxService.createShoeBox(shoeBox);
			if(i<800) {
				Shoe shoe = Shoe.returnShoe();
				shoeService.createShoe(shoe);
				if(i<700) {
					String combinNumber = shoeBox.getShoeBoxNumber() + shoe.getShoeNumber();
					String tempFPNumber= combinNumber.substring(combinNumber.length()/2-6, combinNumber.length()/2+6);
					StringBuffer sb = new StringBuffer(tempFPNumber);
					sb.insert(tempFPNumber.length()/2, "-");
					ShoeFP shoeFP = ShoeFP.returnShoeFP();
					shoeFP.setShoe(shoe);
					shoeFP.setShoeFpName(shoe.getShoeName()+shoeBox.getShoeBoxName());
					shoeFP.setShoeFpNumber(sb.toString());
					shoeFP.setShoeBox(shoeBox);
					shoeFP = shoeFPService.createShoeFP(shoeFP);
				}
			}			
		}
	}
}
