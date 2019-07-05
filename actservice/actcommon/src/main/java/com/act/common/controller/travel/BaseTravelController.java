package com.act.common.controller.travel;

import com.act.common.biz.config.BizCommonConfig.ACT_MEMBER_STATUS;
import com.act.common.domain.act.TravelActInfo;
import com.act.common.domain.actmember.ActMember;
import com.act.common.domain.actnotice.ActNoticer;
import com.act.common.mapper.ActMemberMapper;
import com.act.common.mapper.ActNoticerMapper;
import com.act.common.mapper.TravelActmapper;
import com.act.common.service.travel.TravelActService;
import com.act.datasource.base.util.CurrentUserUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

/**
 * Created by steven on 2019/6/25.
 */
@Slf4j
public abstract class BaseTravelController {
  @Autowired
  protected  TravelActService travelActService;

  @Autowired
  protected TravelActmapper travelActmapper;

  @Autowired
  protected ActMemberMapper actMemberMapper;

  @Autowired
  protected ActNoticerMapper actNoticerMapper;

  protected void setIsJoin(TravelActInfo vo){
    if(vo.getCreateUser().equals(CurrentUserUtil.getUserId())){
      vo.setIsJoin(ACT_MEMBER_STATUS.CREATOR.value);
      return;
    }
    QueryWrapper<ActMember> tmpQuery = new QueryWrapper();
    tmpQuery.lambda()
        .eq(ActMember::getActId,vo.getId())
        .eq(ActMember::getUserId,CurrentUserUtil.getUserId())
        .eq(ActMember::getActType,vo.getActType());
    if(!CollectionUtils.isEmpty(actMemberMapper.selectList(tmpQuery))){
      vo.setIsJoin(ACT_MEMBER_STATUS.JOINER.value);
      return;
    }
    QueryWrapper<ActNoticer> tmpQuery1 = new QueryWrapper();
    tmpQuery1.lambda()
        .eq(ActNoticer::getActId,vo.getId())
        .eq(ActNoticer::getUserId,CurrentUserUtil.getUserId())
        .eq(ActNoticer::getActType,vo.getActType());
    if(!CollectionUtils.isEmpty(actNoticerMapper.selectList(tmpQuery1))){
      vo.setIsJoin(ACT_MEMBER_STATUS.NOTICER.value);
      return;
    }
    vo.setIsJoin(ACT_MEMBER_STATUS.NORELATION.value);
    return;
  }
}
