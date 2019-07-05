package com.act.common.biz.aop;

import com.act.datasource.base.entiy.BaseDO;
import lombok.var;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by steven on 2019/6/25.
 */
@Aspect
@Component
public class MapperAop {
  @Pointcut("execution(* com.act..*.mapper.*.insert(..) )")
  public void excute(){}
  @Around("excute()")
  public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
    var[] objs=pjp.getArgs();
    for(Object obj:objs){
      if(obj instanceof BaseDO){
        BaseDO baseDo= (BaseDO) obj;
        baseDo.initDo();
      }
    }
    return pjp.proceed();
  }
}
