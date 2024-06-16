package org.example.shopapp.Interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Aspect
@Component
public class LoggingInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping) && " +
            "execution(* org.example.shopapp.Controller.UserController.register(..))")
    public void registerMethod() {}

    @Around("registerMethod()")
    public Object logRegisterMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] parameters = joinPoint.getArgs();

        logger.info("Class " + className + ", method " + methodName + " called with parameters: " + Arrays.deepToString(parameters));

        Object result = joinPoint.proceed();

        logger.info("Method " + methodName + " returned with result: " + result);

        return result;
    }
}
