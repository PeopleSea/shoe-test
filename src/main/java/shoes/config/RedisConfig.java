package shoes.config;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object o, Method method, Object... objects) {
				StringBuilder sb = new StringBuilder();
				sb.append(o.getClass().getName());
				sb.append(method.getName());
				for (Object obj : objects) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}

	
	@Bean 
	public CacheManager cacheManager(RedisConnectionFactory factory) {
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

//      解決查詢緩存轉換異常的問題
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
		
//      配置序列化（解决亂碼的問題）
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofSeconds(60))
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .disableCachingNullValues();
		
//		設定要緩存的名稱
		Set<String> cacheNames =  new HashSet<>();
	    cacheNames.add("shoe-redis-cache");
	    cacheNames.add("shoeBox-redis-cache");
	    
//	          分別給各個緩存不同設定
	    Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
	    configMap.put("shoe-redis-cache", config);
	    configMap.put("shoeBox-redis-cache", config.entryTtl(Duration.ofSeconds(120)));
	
		RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
				.initialCacheNames(cacheNames)
				.withInitialCacheConfigurations(configMap)
				.build();
		
		return cacheManager;
	}

	/**
     * 由於原生的redis自動裝配，在儲存key和value時，沒有設置序列化方式，故自己創建redisTemplate實例
     */
	@Bean(name="redisTemplate")
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, String> template = new RedisTemplate<>();
		
		RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();
//		使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默認使用JDK的序列化方式）
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		
		ObjectMapper om = new ObjectMapper();
//		指定要序列化的域，field,get和set,以及修飾符範圍，ANY是有包括private和public
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//		指定序列化輸入的類型，類必須是非final修飾的，final修飾的類，比如String,Integer等會跑出異常
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		
		jackson2JsonRedisSerializer.setObjectMapper(om);
//		配置連接工廠
		template.setConnectionFactory(factory);
//		開啟事務管理
		template.setEnableTransactionSupport(true);
//        template.setEnableDefaultSerializer(false); // 將任何序列化器設置為null，並使用原始字串數組來使用RedisTemplate

//		使用StringRedisSerializer来序列化和反序列化redis的key值, key序列化方式 stringRedisSerializer
		template.setKeySerializer(stringRedisSerializer);
//		值採用json序列化, value序列化 jackson2JsonRedisSerializer 
		template.setValueSerializer(jackson2JsonRedisSerializer);
//		設置hash key 和value序列化模式
		template.setHashKeySerializer(stringRedisSerializer);
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();

		return template;
	}
	
	/**
     * 解决jdk1.8中新时间API的序列化时出现com.fasterxml.jackson.databind.exc.InvalidDefinitionException的问题
     */
    @Bean
    public ObjectMapper serializingObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

	/*
	 * @Bean public CacheManager cacheManager(RedisConnectionFactory factory) {
	 * RedisCacheConfiguration config =
	 * RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(1));
	 * RedisCacheManager cacheManager =
	 * RedisCacheManager.builder(factory).cacheDefaults(config).build(); return
	 * cacheManager; }
	 * 
	 * @Bean public RedisTemplate<String, String>
	 * redisTemplate(RedisConnectionFactory factory) { RedisTemplate<String, String>
	 * redisTemplate = new RedisTemplate<String, String>();
	 * redisTemplate.setConnectionFactory(factory); return redisTemplate; }
	 */

}