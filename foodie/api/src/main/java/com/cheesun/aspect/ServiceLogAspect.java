package com.cheesun.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * AOP通知
 * 1 前置通知：方法调用之前通知
 * 2 后置通知：方法正常调用之后通知
 * 3 环绕通知：之前之后分别通知
 * 4 异常通知：方法调用异常时通知
 * 5 最终通知：方法调用之后通知
 */

/**
 * @author cheesun
 * @date 2020/11/15
 */
@Aspect
@Component
public class ServiceLogAspect {

    public static final Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);

    /**
     * 切面表达式：
     * execution 代表所要执行的表达式主体
     * 第一处 * 代表方法返回类型 *代表所有类型
     * 第二处 包名 代表切面监控的类所在的包
     * 第三处 .. 代表上述包及其下所有子包
     * 第四处 * 代表类名 *代表所有类名
     * 第五处 *(..) *代表类中的方法名，(..)代表所有方法参数可以是任何参数
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.cheesun.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("=== 开始执行 {}.{} ===", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName());

        // 记录开始时间
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        // 记录结束时间
        long end = System.currentTimeMillis();

        long takeTime = end - begin;
        if (takeTime > 3000) {
            logger.error("=== 执行结束，耗时：{}毫秒 ===", takeTime);
        } else if (takeTime > 2000) {
            logger.warn("=== 执行结束，耗时：{}毫秒 ===", takeTime);
        } else {
            logger.info("=== 执行结束，耗时：{}毫秒 ===", takeTime);
        }

        return result;
    }

    /**
     * 记录调用api接口的来源
     * @param joinPoint
     * @throws Throwable
     */
    @Before("execution(* com.cheesun.controller..*.*(..))")
    public void recordRequest(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("=== 客户端：{} 调用 {}.{}", request.getRemoteAddr(), joinPoint.getTarget().getClass(), joinPoint.getSignature().getName());
        logger.info("=== 当前类加载器：{} ===", this.getClass().getMethods());
    }
}
