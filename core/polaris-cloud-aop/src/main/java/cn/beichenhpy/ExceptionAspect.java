package cn.beichenhpy;

import cn.beichenhpy.exception.NeedRollback;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author beichenhpy
 * @apiNote Exception切面处理demo spring执行顺序：aspect -> controllerAdvice -> interceptor -> filter
 * <p>因此 AfterThrowing后也会执行ControllerAdvice 可与 seata结合实现全局回滚
 * @version 1.0.0
 */
@Aspect
@Component
@Slf4j
public class ExceptionAspect {

    @Before("execution(* cn.beichenhpy.*..*.*(..))")
    public void before(JoinPoint joinpoint) {
        MethodSignature signature = (MethodSignature) joinpoint.getSignature();
        final Method method = signature.getMethod();
        //这里可以获取xid
        log.info("intercept method: "+method.getName());
    }

    @AfterThrowing(throwing = "e",pointcut = "execution(* cn.beichenhpy.*..*.*(..))")
    public void afterThrow(Throwable e){
        log.info("method call cn.beichenhpy.exception: "+e.getMessage());
        //这里可以回滚事务
        if (e instanceof NeedRollback){
            //do rollback
            log.info("回滚事务");
        }
    }

}

