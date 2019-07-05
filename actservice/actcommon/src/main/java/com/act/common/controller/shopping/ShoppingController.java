package com.act.common.controller.shopping;

import com.act.common.biz.config.BizCommonConfig.ACT_STATUS;
import com.act.common.domain.act.ShoppingActDO;
import com.act.common.domain.actscope.ShoppingActScope;
import com.act.common.mapper.RuleMapper;
import com.act.common.mapper.ShoppingMapper;
import com.act.common.mapper.ShoppingScopeMapper;
import com.act.common.vo.ReqShoppingActVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by steven on 2019/7/4.
 */
@RestController
@RequestMapping("/act/shopping")
@Slf4j
public class ShoppingController extends BaseShoppingController{

  @Autowired
  ShoppingMapper shoppingMapper;

  @Autowired
  ShoppingScopeMapper shoppingScopeMapper;

  @Autowired
  RuleMapper ruleMapper;

  @Autowired
  private DataSourceTransactionManager payManager;
  @PostMapping("/tmp")
  public R<ReqShoppingActVo> createAct(@RequestBody ReqShoppingActVo inputVo){
      ShoppingActDO shoppingActDO = inputVo.getShoppingActDO();
      shoppingActDO.setStatus(ACT_STATUS.TMP.value);
    TransactionDefinition def = new DefaultTransactionDefinition();
    TransactionStatus status = payManager.getTransaction(def);
    try{
      List<String> rules= Arrays.asList(shoppingActDO.getRuleIds().split(","));
      rules.stream().forEach(ruleId->{

      });
      shoppingMapper.insert(shoppingActDO);
      Optional.ofNullable(inputVo.getShoppingActScope()).ifPresent(list->{
        list.stream().forEach(scopeDo->{
          QueryWrapper<ShoppingActScope> queryWrapper = new QueryWrapper<>();
          queryWrapper.lambda()
              .eq(ShoppingActScope::getActId,shoppingActDO.getId())
              .eq(ShoppingActScope::getGoodsId,scopeDo.getGoodsId())
              .eq(ShoppingActScope::getShopId,scopeDo.getShopId());
          List list1=shoppingScopeMapper.selectList(queryWrapper);
          if(!CollectionUtils.isEmpty(list1)){
            return;
          }

          shoppingScopeMapper.insert(scopeDo);
        });
      });
      payManager.commit(status);
    }catch (Exception e){
      log.error("error",e);
      payManager.rollback(status);
    }
      return R.ok(inputVo);
  }

  @PostMapping("/commit/{id}")
  public R<ShoppingActDO> commit(@PathVariable Long id){
      checkOptPermision(id);
      return null;
  }

}
