package com.act.common.controller.shopping;

import com.act.common.biz.config.BizCommonConfig.ACT_STATUS;
import com.act.common.biz.config.BizCommonConfig.BizCode;
import com.act.common.biz.exception.BizException;
import com.act.common.domain.act.ShoppingActDO;
import com.act.common.domain.act.TravelActInfo;
import com.act.common.mapper.ShoppingMapper;
import com.act.datasource.base.util.CurrentUserUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

/**
 * Created by steven on 2019/7/4.
 */
@Slf4j
public abstract class BaseShoppingController {

  @Autowired
  ShoppingMapper shoppingMapper;

  protected void checkOptPermision(Long id){
    QueryWrapper<ShoppingActDO> queryWrapper = new QueryWrapper();
    queryWrapper.lambda()
        .eq(ShoppingActDO::getCreateUser, CurrentUserUtil.getUserId())
        .eq(ShoppingActDO::getId,id)
        .eq(ShoppingActDO::getStatus, ACT_STATUS.TMP.value);
    List<ShoppingActDO> list=shoppingMapper.selectList(queryWrapper);
    if(CollectionUtils.isEmpty(list)){
      throw new BizException(BizCode.DATA_PERMISSION.name());
    }
  }



}
