package org.ssmmaven7.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.ssmmaven7.dao.IJedisClient;
import org.ssmmaven7.model.Role;
import org.ssmmaven7.model.User;
import org.ssmmaven7.service.IRedisService;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import util.Consol;
import util.JsonUtil;

public class JedisClient implements IJedisClient{
	
	@Autowired
	private JedisPool jedisPool;
	private int expiredTime = 60*60*24*2;  //设置过期时间为两天   秒为单位    60second * 60minute *24Hour *2day

	public Jedis createJedis(){
		Jedis jedis = jedisPool.getResource();
		return jedis;
	}
	
	@Override
	public String get(String key) {
		// TODO Auto-generated method stub
		Jedis jedis = this.createJedis();
		String rs = jedis.get(key);
        //解开注释即可体验过期策略   不管是在get函数还是在set函数中设置同一个key的过期时间，结构都是同样的效果，设置的都是同一个key的过期时间而不是函数的过期时间
		jedis.close();
		return rs;
	}

	@Override
	public String set(String key, String value) {
		// TODO Auto-generated method stub
		Jedis jedis = this.createJedis();
		String rs = jedis.set(key, value);
		 //解开注释即可体验过期策略
//      System.out.println("key :  "+key);
//      System.out.println("查看key的剩余生存时间："+jedis.ttl(key));
		jedis.close();
		return rs;
	}

	@Override
	public String hget(String hkey, String key) {
		// TODO Auto-generated method stub
		Consol.log("jedispool:  "+ jedisPool );
		Jedis jedis = this.createJedis();
		Consol.log("jedis:  "+jedis);
		String rs = jedis.hget(hkey, key);
		jedis.close();
 		return rs;
	}

	@Override
	public long hset(String hkey, String key, String value) {
		// TODO Auto-generated method stub
		Consol.log("jedisPool:  "+jedisPool);
		Jedis jedis = this.createJedis();
		Consol.log("jedis:  "+jedis);
		long rs = jedis.hset(hkey, key,value);
        //解开注释即可体验过期策略
      System.out.println("key :  "+key);
      System.out.println("查看key001的剩余生存时间："+jedis.ttl(hkey));
		jedis.close();
		return rs;
	}

	@Override
	public long incr(String key) {
		// TODO Auto-generated method stub
		Jedis jedis = this.createJedis();
		long rs = jedis.incr(key);
		jedis.close();
		return rs;
	}

	@Override
	public long expire(String key, int second) {
		// TODO Auto-generated method stub
		Jedis jedis = this.createJedis();
		long rs = jedis.expire(key, second);
		jedis.close();
		return rs;
	}
	/**
	 *返回指定key的声誉生存时间 
	 */
	@Override
	public long ttl(String key) {
		// TODO Auto-generated method stub
		Jedis jedis = this.createJedis();
		long rs = jedis.ttl(key);
		jedis.close();
		return rs;
	}

	@Override
	public long del(String key) {
		// TODO Auto-generated method stub
		Jedis jedis = this.createJedis();
		long rs = jedis.del(key);
		jedis.close();
		return rs;
	}

	@Override
	public long hdel(String hkey, String key) {
		// TODO Auto-generated method stub
		Jedis jedis = this.createJedis();
		long rs = jedis.hdel(hkey, key);
		jedis.close();
		return rs;
	}

	@Override
	public long zadd(String key, double score, User user) {
		// TODO Auto-generated method stub
		Jedis jedis = this.createJedis();
		//将Object转化为JSON格式，再将json格式数据 转化为string
		String jsonString = JsonUtil.objectToJsonString(user);
		long rs = jedis.zadd(key, score, jsonString);
		jedis.close();
		return rs;
	}

	//getAll
	@Override
	public Set<String> zrange(String key, long start, long end) {
		// TODO Auto-generated method stub
		Jedis jedis = this.createJedis();
		Set<String> rsSet = jedis.zrange(key, start, end);
		jedis.close();
		return rsSet;
	}

	@Override
	public long zaddList(String key, List<User> userList) {
		// TODO Auto-generated method stub
		Jedis jedis = this.createJedis();
		Long rs = null;
		for(int i=0; i<userList.size(); i++){
			rs = jedis.zadd(key, userList.get(i).getScore(), JsonUtil.objectToJsonString(userList));
			Consol.log("jedis:  "+rs.toString());
		}
		jedis.close();
		return rs;
	}

	@Override
	public Set<String> getTopLast(String key, long start, long end) {
		// TODO Auto-generated method stub
		Jedis jedis = this.createJedis();
		Set<String> rsSet  = jedis.zrange(key, start, end);
		jedis.close();
		return rsSet;
	}

	@Override
	public Map hgetAll(String key) {
		// TODO Auto-generated method stub
		Jedis jedis = this.createJedis();
		Map<String, String> map = jedis.hgetAll(key);
		jedis.close();
		return map;
	}

	@Override
	public Set<String> hkeys(String key) {
		// TODO Auto-generated method stub
		Jedis jedis = this.createJedis();
		Set<String> rsSet = jedis.hkeys(key);
		jedis.close();
		return rsSet;
	}
}
