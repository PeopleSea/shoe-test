package shoes.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import shoes.common.excetion.IErrorCode;
import shoes.common.excetion.ShoeServiceBaseException;
import shoes.model.Shoe;
import shoes.persistence.springdata.IShoeRepository;
import shoes.services.ShoeService;

@Service
public class ShoeServiceImpl implements Serializable, ShoeService {

	private static final long serialVersionUID = 6637349134047596784L;

	@Autowired
	private IShoeRepository shoeRepository;

	@Override
	public Shoe createShoe(Shoe shoe) throws ShoeServiceBaseException {
		return this.shoeRepository.saveAndFlush(shoe);

	}

	@Cacheable(cacheNames = "shoe", key = "#id")
	public Shoe getShoe(String id) {
		Shoe shoe = this.shoeRepository.findById(id).get(0);
		return shoe;
	}

	@Override
	public List<Shoe> queryAllShoes() throws ShoeServiceBaseException {
		try {
			return (List<Shoe>) this.shoeRepository.findAll();
		} catch (Exception ex) {
			throw new ShoeServiceBaseException(IErrorCode.UNKNOWN_EXCEPTION, ex.getMessage(), ex);
		}
	}

	@Override
	public void saveShoes(List<Shoe> shoes) throws ShoeServiceBaseException {
		try {
			this.shoeRepository.saveAll(shoes);
		} catch (Exception ex) {
			throw new ShoeServiceBaseException(IErrorCode.UNKNOWN_EXCEPTION, ex.getMessage(), ex);
		}
	}
}
