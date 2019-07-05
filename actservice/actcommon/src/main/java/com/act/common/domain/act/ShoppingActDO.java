package com.act.common.domain.act;

import com.baomidou.mybatisplus.annotation.TableName;
import javax.validation.constraints.NotNull;
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

  private String rules;

  private String benifitId;

  @Override
  public String getActType() {
    return "shopping";
  }
}
