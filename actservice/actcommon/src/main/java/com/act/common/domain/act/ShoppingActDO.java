package com.act.common.domain.act;

import com.act.common.mapper.ShoppingMapper;
import com.baomidou.mybatisplus.annotation.TableName;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
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
@TableName("tb_act_shop")
public class ShoppingActDO extends BaseActInfoDO{

  private String pictures;
  @NotNull
  private String title;
  @NotNull
  private String ruleIds;
  @NotNull
  private String benifitIds;
  @NotNull
  private String content;

  @Override
  public String getActType() {
    return "shopping";
  }

  @Override
  public Class getSingleMapper() {
    return ShoppingMapper.class;
  }
}
