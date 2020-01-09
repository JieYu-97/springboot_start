package com.baizhi.cache;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.locks.ReadWriteLock;

public class MyCache implements Cache {

    private final String id;

    public MyCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        RedisTemplate redisTemplate = (RedisTemplate) MyWebAware.getByName("redisTemplate");
        redisTemplate.opsForHash().put(id, key, value);
    }

    @Override
    public Object getObject(Object key) {
        RedisTemplate redisTemplate = (RedisTemplate) MyWebAware.getByName("redisTemplate");
        Object o = redisTemplate.opsForHash().get(id, key);
        return o;
    }

    @Override
    public Object removeObject(Object o) {
        return null;
    }

    @Override
    public void clear() {
        RedisTemplate redisTemplate = (RedisTemplate) MyWebAware.getByName("redisTemplate");
        redisTemplate.opsForHash().delete(id);
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
