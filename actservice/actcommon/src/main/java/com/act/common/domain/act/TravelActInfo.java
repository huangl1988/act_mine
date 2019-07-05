package com.act.common.domain.act;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by steven on 2019/6/24.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("tb_travel_act")
public class TravelActInfo extends BaseActInfoDO {
  private String destination;
  @NotNull
  private Integer memNumber;

  private String remarks;

  private BigDecimal joinAmount;
  @ApiModelProperty(name="isJoin",value = "活动参与标识 1：参与者/2：发起者/3：关注/4：么有关系")
  @TableField(exist = false)
  private Integer isJoin;

  @Override
  public String getActType() {
    return "travel";
  }


}
