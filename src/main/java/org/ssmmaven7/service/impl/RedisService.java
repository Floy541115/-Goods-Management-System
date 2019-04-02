package org.ssmmaven7.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssmmaven7.model.User;
import org.ssmmaven7.redis.JedisClient;
import org.ssmmaven7.service.IRedisService;
@Service
public class RedisService implements IRedisService{
	
	@Autowired
	private JedisClient jedisClient;
	
	public String get(String key){
		return jedisClient.get(key);
	}
	
	public String set(String key, String value){
		return jedisClient.set(key, value);
	}

	public String hget(String hkey, String key) {
		return jedisClient.hget(hkey, key);
	}

	public long hset(String hkey, String key, String value) {
	    return jedisClient.hset(hkey, key, value);
	}

	public long incr(String key) {
		return jedisClient.incr(key);
	}

	public long del(String key) {
		return jedisClient.del(key);
	}

	public long hdel(String hkey, String key) {
		return jedisClient.hdel(hkey, key);
	}

	public long zadd(String key, double score, User user) {
		return jedisClient.zadd(key, score, user);
	}

	//getAll
	public Set<String> zrange(String key, long start, long end) {
		return jedisClient.zrange(key, start, end);
	}

	public long zaddList(String key, List<User> userList) {
		return jedisClient.zaddList(key, userList);
	}

	public Set<String> getTopLast(String key, long start, long end) {
		return jedisClient.getTopLast(key, start, end);
	}

	@Override
	public Map hgetAll(String key) {
		// TODO Auto-generated method stub
		return jedisClient.hgetAll(key);
	}

	@Override
	public Set<String> hkeys(String key) {
		// TODO Auto-generated method stub
		return jedisClient.hkeys(key);
	}
}
