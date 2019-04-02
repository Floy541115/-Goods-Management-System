package redis;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import util.Consol;

//鎸囧畾bean娉ㄥ叆鐨勯厤缃枃浠�
@ContextConfiguration(locations = {"classpath:application-context.xml"})
//浣跨敤鏍囧噯鐨凧Unit @RunWith 娉ㄩ噴杩愯Spring Test Runner
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest extends AbstractJUnit4SpringContextTests{
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private JedisPool jedisPool;
	
	/*
	 *娴嬭瘯鎻掑叆涓庤幏鍙�Redis鐨勬暟鎹�
	 */
	@Test
	public void TestCache()throws InterruptedException{
		Consol.log("jedisPool閰嶇疆浣跨敤锛歳edistemplate杩涜鎿嶄綔");
		//鎻掑叆涓�潯鏁版嵁
		try {
			redisTemplate.opsForHash().put("user", "name", "222");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Consol.log("22222222222222");
		//璁剧疆澶辩湢鏃堕棿6绉�
	//	redisTemplate.expire("user", 6, TimeUnit.SECONDS);
		//3绉掑悗
//		Thread.sleep(3000);
//		Thread.yield();
		Object object = redisTemplate.opsForHash().get("user", "name");
		Consol.log("3绉掑悗锛�"+object.toString());
		//7绉掑悗鑾峰彇
//		Thread.sleep(4000);
//		Thread.yield();
		object = redisTemplate.opsForHash().get("user", "name");
		Consol.log("7绉掑悗锛�"+object.toString());
		Consol.log("JedisPool閰嶇疆浣跨敤锛�jedis鎿嶄綔");
		
		Jedis jedis = jedisPool.getResource();
		Consol.log("鏈嶅姟鍣↖P鍦板潃锛�"+jedis.configGet("bind"));
		jedis.hset("me", "name", "yy");
		Consol.log("jedis.hset(me name yy): "+jedis.hget("me","name"));
		
		Map<String, String> student = new HashMap<String, String>();
		student.put("name", "ff");
		student.put("id", "1");
		jedis.hmset("student", student);
		
		//鍏抽棴杩炴帴
		if (jedis != null) {
			student.clear();
			jedisPool.close();
		}
		Consol.log("jedisPool鎿嶄綔缁撴潫");
	}
}
