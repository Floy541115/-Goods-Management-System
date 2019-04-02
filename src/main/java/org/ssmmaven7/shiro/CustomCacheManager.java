package org.ssmmaven7.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.JedisPool;

public class CustomCacheManager implements CacheManager{
	
	private JedisPool jedisPool ;

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		// TODO Auto-generated method stub
		return new ShiroRedisCache<>(name,jedisPool);
	}
	
	public void setJedisPool(JedisPool jedisPool){
		this.jedisPool = jedisPool;
	}
	public JedisPool getJedisPool(){
		return jedisPool;
	}

}
