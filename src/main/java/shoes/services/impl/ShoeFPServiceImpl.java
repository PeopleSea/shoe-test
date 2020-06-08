package shoes.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoes.common.excetion.IErrorCode;
import shoes.common.excetion.ShoeFPServiceBaseException;
import shoes.common.excetion.ShoeServiceBaseException;
import shoes.model.ShoeFP;
import shoes.persistence.springdata.IShoeFPRepository;
import shoes.services.ShoeFPService;

@Service
public class ShoeFPServiceImpl implements Serializable, ShoeFPService {

	private static final long serialVersionUID = 4661755345303226701L;
	
//	private static final Logger logger = LoggerFactory.getLogger(ShoeBoxServiceImpl.class.getName());
	
	@Autowired
	private IShoeFPRepository shoeFPRepository;

	@Override
	public ShoeFP createShoeFP(ShoeFP shoeFP)throws ShoeFPServiceBaseException {
				
		return shoeFPRepository.saveAndFlush(shoeFP);
		
	}

	@Override
	public List<ShoeFP> queryAllFP() throws ShoeFPServiceBaseException {
		try {
			return (List<ShoeFP>) this.shoeFPRepository.findAll();
		} catch (Exception ex) {
			throw new ShoeServiceBaseException(IErrorCode.UNKNOWN_EXCEPTION, ex.getMessage(), ex);
		}
	}

	@Override
	public ShoeFP queryById(String parameterId) throws ShoeFPServiceBaseException {
		this.shoeFPRepository.getOne(Long.valueOf(parameterId));
		return null;
	}


//	@Override
//	public ShoeFP updateShoeFPName(ShoeFP shoeFP, String shoeFPName) throws ShoeFPServiceBaseException {
//		return null;
//	}

//	@Override
//	public ShoeFP queryByShoeFPId(String paramInteger) throws ShoeFPServiceBaseException {
//		return null;
//	}

}
