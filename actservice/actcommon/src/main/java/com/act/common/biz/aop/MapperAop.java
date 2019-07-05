package com.act.common.biz.aop;

import com.act.common.biz.config.BizCommonConfig.ACT_STATUS;
import com.act.common.biz.config.BizCommonConfig.BizCode;
import com.act.common.biz.exception.BizException;
import com.act.common.domain.act.BaseActInfoDO;
import com.act.common.util.SpringUtil;
import com.act.datasource.base.entiy.BaseDO;
import com.act.datasource.base.util.CurrentUserUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import lombok.var;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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
      if(obj instanceof BaseActInfoDO){
        BaseActInfoDO baseActInfoDO= (BaseActInfoDO) obj;
        BaseMapper baseMapper=(BaseMapper)SpringUtil.getBean(baseActInfoDO.getSingleMapper());
        QueryWrapper<BaseActInfoDO> queryWrapper=new QueryWrapper();
        queryWrapper.lambda().eq(BaseActInfoDO::getCreateUser, CurrentUserUtil.getUserId()).
            in(BaseActInfoDO::getStatus,
                ACT_STATUS.TMP.value,
                ACT_STATUS.INIT.value,
                ACT_STATUS.AUDIT.value);
        List list=baseMapper.selectList(queryWrapper);
        if(!CollectionUtils.isEmpty(list)){
          throw new BizException(BizCode.CREATE_ACT_LIMIT.name());
        }
      }
    }
    return pjp.proceed();
  }
}
