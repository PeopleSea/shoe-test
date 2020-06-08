package shoes.services;

import java.util.List;

import shoes.common.excetion.ShoeServiceBaseException;
import shoes.model.Shoe;

public interface ShoeService {
	
	Shoe createShoe(Shoe shoe) throws ShoeServiceBaseException;
	
	List<Shoe> queryAllShoes() throws ShoeServiceBaseException;
	
	void saveShoes(List<Shoe> shoes) throws ShoeServiceBaseException;
}
