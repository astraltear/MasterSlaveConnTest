package com.audien.db.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(value=1)
public class ExecutionLoggingAspect implements InitializingBean {
	
	private static final Logger logger = LoggerFactory.getLogger(ExecutionLoggingAspect.class);


	  @Around("execution(* com.audien..*Service.*(..))")
	  public Object doServiceProfiling(ProceedingJoinPoint joinPoint) throws Throwable {
	  
	    logger.info("@Service 시작");
	    
	    //Annotation을 읽어 들이기 위해 현재의 method를 읽어 들인다.
	    final String methodName = joinPoint.getSignature().getName();
	    final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
	    Method method = methodSignature.getMethod();
	    if(method.getDeclaringClass().isInterface()){
	      method = joinPoint.getTarget().getClass().getDeclaredMethod(methodName, method.getParameterTypes());
	    }
	    
	    //Annotation을 가져온다.
	    DataSource dataSource = (DataSource) method.getAnnotation(DataSource.class);
	    if(dataSource != null){
	      //Method에 해당 dataSource관련 설정이 있을 경우 해당 dataSource의 value를 읽어 들인다.
	      ContextHolder.setDataSourceType(dataSource.value	());
	    }else{
	    //따로 annotation으로 datasource를 지정하지 않은 경우에는 메소드 이름으로 판단
	    //get*, select* 의 경우는 default, 그 외의 경우에는 MASTER
	    if(!(method.getName().startsWith("get") || method.getName().startsWith("select"))){
	      ContextHolder.setDataSourceType(DataSourceType.MASTER);
	    }
	  }
	  logger.info("DataSource ===> " + ContextHolder.getDataSourceType());
	  
	  Object returnValue = joinPoint.proceed();
	  ContextHolder.clearDataSourceType();
	  
	  logger.info("@Service 끝");
	  
	  return returnValue;
	  
	  }


	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
