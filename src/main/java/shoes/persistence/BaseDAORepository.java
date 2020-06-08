package shoes.persistence;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDAORepository implements Serializable {

	private static final long serialVersionUID = -9195587029168951301L;
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;
	
	public List getListForPage(String sql, Map<String, Object> parameterMap, Integer offset, Integer length) {
		return getResult(this.entityManager.createQuery(sql), parameterMap, offset, length);
	}

	public List findByHql(String hql, Map<String, Object> parameterMap) {
		return getListForPage(hql, parameterMap, null, null);
	}

	public List findBySql(String sql, Map<String, Object> parameterMap) {
		return getListForPage4SQL(sql, parameterMap, null, null);
	}

	public List getListForPage4SQL(String sql, Map<String, Object> parameterMap, Integer offset, Integer length) {
		return getResult(this.entityManager.createNativeQuery(sql), parameterMap, offset, length);
	}

	private List getResult(Query query, Map<String, Object> parameterMap, Integer offset, Integer length) {
		if (!parameterMap.isEmpty())
			for (String key : parameterMap.keySet()) {
				if (parameterMap.get(key) instanceof List) {
					query.setParameter(key, parameterMap.get(key));
					continue;
				}
				query.setParameter(key, parameterMap.get(key));
			}
		if (offset != null && length != null) {
			query.setFirstResult(offset.intValue());
			query.setMaxResults(length.intValue());
		}
		return query.getResultList();
	}

	public Map<String, Object> findObjectListByJpl(String jpql, Map<String, Object> parameterMap, Integer pageNo,
			Integer maxResult) {
		Map<String, Object> map = new HashMap<>();
		Integer DEFAULT_PAGE_NO = Integer.valueOf(1);
		Integer DEFAULT_MAX_RESULT = Integer.valueOf(10);
		Query query = this.entityManager.createQuery(jpql);
		query.setFirstResult(
				(pageNo != null && pageNo.intValue() != 0) ? ((pageNo.intValue() - 1) * maxResult.intValue())
						: ((DEFAULT_PAGE_NO.intValue() - 1) * DEFAULT_PAGE_NO.intValue()));
		query.setMaxResults(
				((maxResult != null && maxResult.intValue() != 0) ? maxResult : DEFAULT_MAX_RESULT).intValue());
		if (MapUtils.isNotEmpty(parameterMap))
			for (String key : parameterMap.keySet()) {
				if (parameterMap.get(key) instanceof List) {
					query.setParameter(key, parameterMap.get(key));
					continue;
				}
				query.setParameter(key, parameterMap.get(key));
			}
		Integer totalCount = getTotalCount(jpql, parameterMap);
		Integer totalPage = Integer.valueOf(totalCount.intValue() / query.getMaxResults()
				+ ((totalCount.intValue() % query.getMaxResults() == 0) ? 0 : 1));
		Integer newPageNo = pageNo;
		if (newPageNo.intValue() > totalPage.intValue())
			newPageNo = totalPage;
		map.put("getResults", query.getResultList());
		map.put("getTotalElements", totalCount);
		map.put("pageNo", newPageNo);
		map.put("maxResults", Integer.valueOf(query.getMaxResults()));
		map.put("getTotalPages", totalPage);
		return map;
	}

	public Integer getTotalCount(String jpql, Map<String, Object> parameterMap) {
		Query query = this.entityManager.createQuery(jpql);
		if (MapUtils.isNotEmpty(parameterMap))
			for (String key : parameterMap.keySet()) {
				if (parameterMap.get(key) instanceof List) {
					query.setParameter(key, parameterMap.get(key));
					continue;
				}
				query.setParameter(key, parameterMap.get(key));
			}
		return Integer.valueOf(query.getResultList().size());
	}
}
