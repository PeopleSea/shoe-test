package shoes.controller;

import java.util.concurrent.TimeUnit;

import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.stereotype.Component;

@Component
public class CacheConfig implements JCacheManagerCustomizer{
    @Override
    public void customize(javax.cache.CacheManager cacheManager) {
        cacheManager.createCache("shoe", new MutableConfiguration<>()
                .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new Duration(TimeUnit.MINUTES, 1)))
                .setStoreByValue(false)
                .setStatisticsEnabled(true));
    }
}