package shoes.services;

import java.util.List;

import shoes.common.excetion.ShoeBoxServiceBaseException;
import shoes.model.ShoeBox;


public interface ShoeBoxService {
	
	ShoeBox createShoeBox(ShoeBox shoeBox) throws ShoeBoxServiceBaseException;
	
	List<ShoeBox> queryAllBoxs() throws ShoeBoxServiceBaseException;
}
