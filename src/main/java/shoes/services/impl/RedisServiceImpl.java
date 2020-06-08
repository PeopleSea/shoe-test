package shoes.services.impl;

import org.springframework.stereotype.Service;

import shoes.services.RedisService;
import shoes.utils.RedisUtil;

@Service
public class RedisServiceImpl implements RedisService {

	@Override
	public void setObj(String key, Object obj, long timeout) {
		RedisUtil.set(key, obj, timeout);
	}

	@Override
	public Object getObj(String key) {
		return RedisUtil.get(key);
	}
}