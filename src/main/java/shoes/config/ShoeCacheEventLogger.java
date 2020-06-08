package shoes.config;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShoeCacheEventLogger implements CacheEventListener<Object, Object> {
	private static final Logger logger = LoggerFactory.getLogger(ShoeCacheEventLogger.class);

	@Override
	public void onEvent(CacheEvent cacheEvent) {
		logger.info("shoe caching event {} {} {} {}", 
				cacheEvent.getType(), 
				cacheEvent.getKey(),
				cacheEvent.getOldValue(), 
				cacheEvent.getNewValue());
	}
}