package shoes.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shoes.common.excetion.IErrorCode;
import shoes.common.excetion.ShoeBoxServiceBaseException;
import shoes.common.excetion.ShoeServiceBaseException;
import shoes.model.ShoeBox;
import shoes.persistence.springdata.IShoeBoxRepository;
import shoes.services.ShoeBoxService;

@Service
public class ShoeBoxServiceImpl implements Serializable, ShoeBoxService {

	private static final long serialVersionUID = 4661755345303226701L;
	
//	private static final Logger logger = LoggerFactory.getLogger(ShoeBoxServiceImpl.class.getName());
	
	@Autowired
	private IShoeBoxRepository shoeBoxRepository;

	@Override
	public ShoeBox createShoeBox(ShoeBox shoeBox) throws ShoeBoxServiceBaseException {
		return this.shoeBoxRepository.saveAndFlush(shoeBox);
	}

	@Override
	public List<ShoeBox> queryAllBoxs() throws ShoeBoxServiceBaseException {
		try {
			return (List<ShoeBox>) this.shoeBoxRepository.findAll();
		} catch (Exception ex) {
			throw new ShoeServiceBaseException(IErrorCode.UNKNOWN_EXCEPTION, ex.getMessage(), ex);
		}
	}

}
