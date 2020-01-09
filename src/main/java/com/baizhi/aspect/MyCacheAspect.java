package com.baizhi.aspect;

import com.baizhi.cache.MyWebAware;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyCacheAspect {

    @Around(value = "@annotation(com.baizhi.aspect.AddAndSelectAnnotation)")
    public Object AddAndSelectAround(ProceedingJoinPoint proceedingJoinPoint) {
        String clazz = proceedingJoinPoint.getTarget().getClass().toString();
        System.out.println(clazz);
        String name = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();
        String key = name;
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            key += arg;
        }
        System.out.println("key--->" + key);
        RedisTemplate redisTemplate = (RedisTemplate) MyWebAware.getByName("redisTemplate");
        Object o = redisTemplate.opsForHash().get(clazz, key);
        if (o != null) {
            return o;
        }
        try {
            Object proceed = proceedingJoinPoint.proceed();
            redisTemplate.opsForHash().put(clazz, key, proceed);
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }

    }

    @Around(value = "@annotation(com.baizhi.aspect.ClearAnnotation)")
    public Object ClearAround(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            Object proceed = proceedingJoinPoint.proceed();
            String clazz = proceedingJoinPoint.getTarget().getClass().toString();
            RedisTemplate redisTemplate = (RedisTemplate) MyWebAware.getByName("redisTemplate");
            redisTemplate.opsForHash().delete(clazz);
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
