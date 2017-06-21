package top.onee.ssm.dao.cache;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import top.onee.ssm.common.CommonConsts;
import top.onee.ssm.expand.util.ProtostuffUtil;

import java.util.List;

/**
 * Redis DAO
 * Created by VOREVER on 23/04/2017.
 */
@Repository
public class RedisDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    /**
     * 序列化方式从redis中获取对象
     *
     * @param key
     * @return
     */
    public <T> T getObject(String key, Class<T> clazz) {
        try {
            ShardedJedis jedis = shardedJedisPool.getResource();
            try {
                byte[] bytes = jedis.get(key.getBytes());
                if (bytes != null) {
                    T obj = ProtostuffUtil.deserializer(bytes, clazz);
                    if (obj != null) {
                        return obj;
                    }
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 序列化方式向redis中存入对象(1小时过期)
     *
     * @param key
     * @param obj
     * @return
     */
    public String putObject(String key, Object obj) {
        return putObject(key, obj, CommonConsts.REDIS_TIMEOUT_HOUR);
    }

    /**
     * 序列化方式向redis中存入对象并设置过期时间(秒)
     *
     * @param key
     * @param obj
     * @return
     */
    public String putObject(String key, Object obj, int expire) {
        try {
            ShardedJedis jedis = shardedJedisPool.getResource();
            try {
                /*存储到redis，成功返回"OK"，失败则返回错误信息*/
                byte[] bytes = ProtostuffUtil.serializer(obj);
                jedis.setex(key.getBytes(), expire, bytes);
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * JSON形式从redis中获取对象数组
     *
     * @param key
     * @return
     */
    public <T> List getList(String key, Class<T> clazz) {
        try {
            ShardedJedis jedis = shardedJedisPool.getResource();
            try {
                String objStr = jedis.get(key);
                if (objStr != null) {
                    return JSON.parseArray(objStr, clazz);
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * JSON形式向redis中存入对象
     *
     * @param key
     * @param obj
     * @return
     */
    public String put(String key, Object obj) {
        try {
            ShardedJedis jedis = shardedJedisPool.getResource();
            try {
                /*存储到redis，成功返回"OK"，失败则返回错误信息*/
                return jedis.setex(key, CommonConsts.REDIS_TIMEOUT, JSON.toJSONString(obj));
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 从Redis中取值
     *
     * @param key
     * @return
     */
    public String getValue(String key) {
        try {
            ShardedJedis jedis = shardedJedisPool.getResource();
            try {
                return jedis.get(key);
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
