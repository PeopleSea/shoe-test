package shoes.services;

import java.util.List;

import shoes.common.excetion.ShoeFPServiceBaseException;
import shoes.model.ShoeFP;

public interface ShoeFPService {

	ShoeFP createShoeFP(ShoeFP shoeFP) throws ShoeFPServiceBaseException;
	
	List<ShoeFP> queryAllFP() throws ShoeFPServiceBaseException;
	
	ShoeFP queryById(String parameterId) throws ShoeFPServiceBaseException;
	
}
