package shoes.persistence;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ShoeRepositoryCustom extends BaseDAORepository implements Serializable {

	private static final long serialVersionUID = 7034429273516217848L;

	private final Logger logger = LoggerFactory.getLogger(ShoeRepositoryCustom.class);
	
	public List<Object[]> findAll() {
		Map<String, Object> keyMap = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		sb.append("select s.* from XY_PAYMENT.PS_OPERATOR s ");
		this.logger.debug("===========ShoeRepositoryCustom.findAll===============", sb.toString());
		return getListForPage4SQL(sb.toString(), keyMap, null, null);
	}
	
}
