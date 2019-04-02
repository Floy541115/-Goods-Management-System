package org.ssmmaven7.shiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import util.Consol;
import util.SerializeUtil;

public class ShiroRedisCache<K,V> implements Cache<K, V>{

	/** key的前缀*/
	public String keyPrefix = "shiro_redis_cache_";  
	private JedisPool jedisPool ;//配有在配置jedisCient的bean就要配扫描吗
	private String name;
	public ShiroRedisCache(String name, JedisPool jedisPool){
		this.name = name;
		this.jedisPool = jedisPool;
	}
	
	public Jedis createJedis(){
		return jedisPool.getResource();
	}
	public String getKeyPrefix(){
		return keyPrefix;
	}
	public void setKeyPrefix(String keyPrefix){
		this.keyPrefix = keyPrefix;
	}
	@Override
	public Object get(Object key) throws CacheException {
		// TODO Auto-generated method stub
		Jedis jedis = this.createJedis();
//		String rs = jedis.get(key.toString());
		byte[] value = jedis.get(this.getByteKey(key));
		Consol.log(keyPrefix+key.toString()+": "+value);
		jedis.close();
		if(value == null)
			return null;
		return  SerializeUtil.objectDeserialization(value);
	}
	
	/** 将shiro的缓存保存到redis中*/
	@Override
	public Object put(Object key, Object value) throws CacheException {
		// TODO Auto-generated method stub
		Jedis jedis = this.createJedis();
		Object rs = jedis.set(getByteKey(key), SerializeUtil.objectSerialization((Serializable)value));  //缓存就是将Object序列化到内存中
		jedis.close();
		Consol.log(keyPrefix+key.toString()+": "+value);
		return rs;
	}

	@Override
	public Object remove(Object key) throws CacheException {
		// TODO Auto-generated method stub
		Jedis jedis = this.createJedis();
		byte[] rs = jedis.get(this.getByteKey(key));
		jedis.del(this.getByteKey(key));
		jedis.close();
		Consol.log(keyPrefix+key.toString()+": ");
		return SerializeUtil.objectDeserialization(rs);
	}

	@Override
	public void clear() throws CacheException {
		// TODO Auto-generated method stub
		Jedis jedis = this.createJedis();
		jedis.flushDB();
		jedis.close();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		Jedis jedis = this.createJedis();
		return jedis.dbSize().intValue();
	}

	@Override
	public Set keys() {
		// TODO Auto-generated method stub
		Jedis jedis = this.createJedis();
		Set<byte[]> keySet = jedis.keys(new String("*").getBytes());
		Set<Object> set = new HashSet<>();
		for(byte[] b : keySet){
			set.add(SerializeUtil.objectDeserialization(b));
		}
		return set;
	}

	@Override
	public Collection values() {
		// TODO Auto-generated method stub
		Jedis jedis = this.createJedis();
		Set keySet = this.keys();
		List<Object> valueList = new ArrayList<Object>();
		for(Object key : keySet){
			byte[] bytes = jedis.get(this.getByteKey(key));
			valueList.add(SerializeUtil.objectDeserialization(bytes));
		}
		jedis.close();
		return valueList;
	}
	
	public byte[] getByteKey(Object key){
		Consol.log(keyPrefix+key.toString());
		if(key instanceof String){
			String preKey = this.keyPrefix + key;
			return preKey.getBytes();
		}else {
			return SerializeUtil.objectSerialization((Serializable)key);
		}
	}
}
