package org.ssmmaven7.aop;

import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ssmmaven7.model.Role;
import org.ssmmaven7.redis.JedisClient;

import com.alibaba.fastjson.JSONArray;

import util.Consol;
import util.JsonUtil;

@Aspect
@Component
public class RedisCacheAspect {
	@Autowired
	private JedisClient jedisClient;
	
//	@Before("@annotation(redisEvict)")
//	public void doBefore(JoinPoint jp, RedisEvict redisEvict){
//		try {
//			String modelNameString = redisEvict.type().getName();
//			jedisClient.del(modelNameString);
//		} catch (Exception e) {
//			// TODO: handle exception
//			Consol.log("清理redis缓存");
//			e.printStackTrace();
//		}
//	}
	//@Pointcut("execution(* org.ssmmaven7.service.impl.RoleService.*(..))")
	@Pointcut("execution(* org.ssmmaven7.service.impl.RoleService.findAllRole(..))")
	public void roleRedisAspect(){}
	
	@SuppressWarnings("unchecked")
	@Around("roleRedisAspect()")
	public String doAround(ProceedingJoinPoint pjp) throws Throwable{
//		 Method me = ((org.aspectj.lang.reflect.MethodSignature)pjp.getSignature()).getMethod();
//       Class modelType = me.getAnnotation(RedisCache.class).type();  
/*		 Class modelType = redisCache.type();
		 String cacheKey = getCacheKey(pjp);
		 Consol.log("cacheKey: "+cacheKey);
		 Consol.log("modelType.getName: "+modelType.getName());*/
		 String value =null;
		 String rs = URLDecoder.decode("", "utf-8");
		 try {
			value = jedisClient.get("role");
		} catch (Exception e) {
			// TODO: handle exception
			Consol.log("获取redis缓存异常");
			e.printStackTrace();
		}
		 //后端查询数据
		Object result = null;
		if(value == null || value == "null"){
			try {
				result = pjp.proceed();    //目标方法，controller中的切点方法list = roleService.findAll();
				//假如切点是service中中的select*(...)，那么目标方法就是select*(...)，调用pjp.proceed()时就会调用select*(...)
				rs = URLDecoder.decode((String)result, "UTF-8");
				jedisClient.set("role", rs);    //如果是删除，那么久设置service返回null，然后就设置该值为空？
				jedisClient.expire("role",60*30);
			} catch (Exception e) {
				// TODO: handle exception
				Consol.log("SQL或缓存异常");
				e.printStackTrace();
			}
		}else{
//			try {
//				Class returnType = ((MethodSignature)pjp.getSignature()).getReturnType();
//				result = JsonUtil.jsonToJavaBean(value, returnType);
//			} catch (Exception e) {
//				// TODO: handle exception
//				Consol.log("格式转换失败,到 后端查询数据");
//				pjp.proceed();
//				e.printStackTrace();
//			}
			rs = URLDecoder.decode(value, "UTF-8");
		}
		//不管缓存中有没有值，不管有没有执行目标方法result = pjp.proceed();;，最后返回到controller中的值都是这个rs
		return rs.replaceAll("\\\\", "");
		
	}
	  // 包名+ 类名 + 方法名 + 参数(多个) 生成Key  
    public String getCacheKey(ProceedingJoinPoint pjp) {  
        StringBuffer key = new StringBuffer();  
        // 包名+ 类名 ：cn.core.serice.product.ProductServiceImpl.productList  
        String packageName = pjp.getTarget().getClass().getName();  
  
        key.append(packageName);  
        // 方法名  
        String methodName = pjp.getSignature().getName();  
        key.append(".").append(methodName);  
  
        // 参数(多个)  
        Object[] args = pjp.getArgs();  
  
        for (Object arg : args) {  
            // 参数  
            key.append(".").append(arg.toString());  
        }  
  
        return key.toString();  
    }  

}

