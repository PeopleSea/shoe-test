package shoes.utils;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import shoes.services.SpringContextUtil;



public class RedisUtil {

	private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);
	
    //@Autowired
    //private static RedisTemplate<String, Object> redisTemplate;

    private static final RedisTemplate<String, Object> redisTemplate = SpringContextUtil.getBean("redisTemplate", RedisTemplate.class);

    /**********************************************************************************
     * redis-公共操作
     **********************************************************************************/

    /**
     * 指定緩存失效時間
     *
     * @param key  鍵
     * @param time 時間(秒)
     * @return
     */
    public static boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
        	logger.error("【redis：指定緩存失效時間-異常】", e);
            return false;
        }
    }

    /**
     * 根據key 獲取過期時間
     *
     * @param key 鍵 不能為null
     * @return 時間(秒) 返回0代表為永久有效;如果該key已經過期,將返回"-2";
     */
    public static long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判斷key是否存在
     *
     * @param key 鍵
     * @return true 存在 false不存在
     */
    public static boolean exists(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
        	logger.error("【redis：判斷{}是否存在-异常】", key, e);
            return false;
        }
    }


    /**********************************************************************************
     * redis-String類型的操作
     **********************************************************************************/

    /**
     * 普通緩存放入
     *
     * @param key   鍵
     * @param value 值
     * @return true成功  false失败
     */
    public static boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
        	logger.error("【redis：普通緩存放入-異常】", e);
            return false;
        }
    }

    /**
     * 普通緩存放入並設置時間
     *
     * @param key   鍵
     * @param value 值
     * @param time  時間(秒) time要大於0 如果time小於等於0 將設置無限期
     * @return true 成功  false 失敗
     */
    public static boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
        	logger.error("【redis：普通緩存放入並設置時間-異常】", e);
            return false;
        }
    }

    /**
     * 遞增
     *
     * @param key   鍵
     * @param delta 要增加幾(大於0)
     * @return
     */
    public static long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("遞增因子必须大於0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 遞減
     *
     * @param key   鍵
     * @param delta 要減少幾(小於0)
     * @return
     */
    public static long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("遞減因子必须大於0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    /**
     * 刪除緩存
     *
     * @param key 可以傳一個值或多個
     */
    @SuppressWarnings("unchecked")
    public static void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }


    /**
     * 獲取緩存
     *
     * @param key   redis的key
     * @param clazz value的class類型
     * @param <T>
     * @return value的實際對象
     */
    public  static <T> T get(String key, Class<T> clazz) {
        Object obj = key == null ? null : redisTemplate.opsForValue().get(key);
        if (!obj.getClass().isAssignableFrom(clazz)) {
            throw new ClassCastException("類轉化異常");
        }
        return (T) obj;
    }

    /**
     * 獲取泛型
     *
     * @param key 鍵
     * @return 值
     */
    public static Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }
}