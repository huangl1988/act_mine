package com.act.common.service.travel;
import com.act.common.biz.config.BizCommonConfig;
import com.act.common.biz.config.BizCommonConfig.ACT_STATUS;
import com.act.common.biz.config.BizCommonConfig.BizCode;
import com.act.common.biz.exception.BizException;
import com.act.common.domain.actmember.ActMember;
import com.act.common.mapper.ActMemberMapper;
import com.act.common.mapper.MineTravelActMapper;
import com.act.common.domain.act.TravelActInfo;
import com.act.common.mapper.TravelActmapper;
import com.act.common.service.BaseService;
import com.act.datasource.base.util.CurrentUserUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by steven on 2019/6/25.
 */
@Service
public class TravelActService extends BaseService{

  @Autowired
  TravelActmapper travelActmapper;

  @Autowired
  MineTravelActMapper mineTravelActMapper;

  @Autowired
  ActMemberMapper actMemberMapper;

  public void insert(TravelActInfo travelActInfo) {
    travelActmapper.insert(travelActInfo);
  }

  public List<TravelActInfo> queryTravel(QueryWrapper<TravelActInfo> queryWrapper) {
    return travelActmapper.selectList(queryWrapper);
  }
  @Transactional
  public void commitTravelAct(Long id) {
    if(1!=mineTravelActMapper.updateTravelStatus(id, ACT_STATUS.TMP.value,ACT_STATUS.INIT.value)){
        throw new BizException(BizCode.DATA_ERROR.name());
    }

    actMemberMapper.insert(ActMember.builder().actId(id)
        .actType(TravelActInfo.builder().build().getActType())
        .userId(CurrentUserUtil.getUserId())
        .build());
  }

  public void deleteAct(Long id) {
    if(1!=mineTravelActMapper.deleteAct(id, ACT_STATUS.TMP.value)){
      throw new BizException(BizCode.DATA_ERROR.name());
    }
  }
}
