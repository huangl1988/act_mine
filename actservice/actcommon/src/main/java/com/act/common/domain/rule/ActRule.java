package com.act.common.domain.rule;

import com.act.datasource.base.entiy.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by steven on 2019/7/5.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("tb_rule")
public class ActRule extends BaseDO{

  private String ruleType;

  private String ruleName;

  private String ruleScopeShopIds;

  private String expression;

  private BigDecimal minRuleCondition;

}
