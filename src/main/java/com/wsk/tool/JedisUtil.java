package com.wsk.tool;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisUtil{
    @Autowired
    private JedisPool jedisPool;
    public String set(String  key,Object value){
        Jedis jedis=jedisPool.getResource();
        String result="";
        if (value instanceof String){
            result=jedis.set(key,value+"");
        }else{
            result=jedis.set(key,JUtil.objToString(value));
        }
        jedis.close();
        return result;
    }
    public String get(String key){
        Jedis jedis=jedisPool.getResource();
        jedis.close();
        return jedis.get(key);
    }
    public <T>T get(String key,Class<T> clazz){
        Jedis jedis=jedisPool.getResource();
        jedis.close();
        return JUtil.strToObject(jedis.get(key),clazz);
    }
    public Boolean exists(String key) {
        Jedis jedis = jedisPool.getResource();
        Boolean result = jedis.exists(key);
        jedis.close();
        return result;
    }
    public Long expire(String key, int seconds) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.expire(key, seconds);
        jedis.close();
        return result;
    }
    public Long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.ttl(key);
        jedis.close();
        return result;
    }
    public Long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }
    public Long decr(String key){
        Jedis jedis=jedisPool.getResource();
        Long result=jedis.decr(key);
        jedis.close();
        return result;
    }
}
