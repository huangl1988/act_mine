package com.act.common.domain.actscope;

import com.act.datasource.base.entiy.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by steven on 2019/7/4.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("tb_act_shop_scope")
public class ShoppingActScope extends BaseDO{

  private String shopId;

  private String goodsId;

  private String actId;

  private String actType;

}
