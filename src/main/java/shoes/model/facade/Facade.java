package shoes.model.facade;

import java.util.ArrayList;
import java.util.List;

import shoes.model.Shoe;
import shoes.model.ShoeBox;
import shoes.model.ShoeFP;

public class Facade {
	private static Shoe shoe;
    private static  ShoeBox shoeBox;
    private static ShoeFP shoeFP;
    
    public Facade(){
    	shoe = new Shoe();
    	shoeBox = new ShoeBox();
    	shoeFP = new ShoeFP();
    }
    
    public static List<Shoe> createShoe(int length) {
    	List<Shoe> shoes = new ArrayList<Shoe>();
    	for(int i=0;i<length;i++) {
    		shoes.add(Shoe.returnShoe());
    	}
    	return shoes;
    }
    
    public static List<ShoeBox> createBox(int length) {
    	List<ShoeBox> shoeBoxs = new ArrayList<ShoeBox>();
    	for(int i=0;i<length;i++) {
    		shoeBoxs.add(ShoeBox.returnShoeBox());
    	}
    	return shoeBoxs;
    }
    
    public static List<ShoeFP> createShoeFP(List<Shoe> shoes, List<ShoeBox> shoeBoxs, int length) {
    	List<ShoeFP> shoeFPs = new ArrayList<ShoeFP>();
    	for(int i=0;i<length;i++) {
    		shoeBox = shoeBoxs.get(i);
    		shoe = shoes.get(i);
    		String combinNumber = shoeBoxs.get(i).getShoeBoxNumber() + shoes.get(i).getShoeNumber();
			String tempFPNumber= combinNumber.substring(combinNumber.length()/2-6, combinNumber.length()/2+6);
			StringBuffer sb = new StringBuffer(tempFPNumber);
			sb.insert(tempFPNumber.length()/2, "-");
			ShoeFP shoeFP = ShoeFP.returnShoeFP();
			shoeFP.setShoe(shoe);
			shoeFP.setShoeFpName(shoe.getShoeName()+shoeBox.getShoeBoxName());
			shoeFP.setShoeFpNumber(sb.toString());
			shoeFP.setShoeBox(shoeBox);
			shoeFPs.add(shoeFP);    		
    	}
    	return shoeFPs;
    }
}
