package top.onee.ssm.dao.cache;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import top.onee.ssm.common.CommonConsts;

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
     * 从redis中获取对象
     * @param key
     * @return
     */
    public <T>List get(String key, Class<T> clazz) {
        try {
            ShardedJedis jedis = shardedJedisPool.getResource();
            try {
                // TODO: 目前采用解析JSON字符串形式，以后优化为反序列化对象形式
                String objStr = jedis.get(key);
                return JSON.parseArray(objStr, clazz);
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 向redis中存入对象
     * @param key
     * @param obj
     * @return
     */
    public String put(String key, Object obj) {
        try {
            ShardedJedis jedis = shardedJedisPool.getResource();
            try {
                // TODO:目前采用转为JSON字符串存储，以后优化为序列化对象存储
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

}
