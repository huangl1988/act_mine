package com.act.common.domain.benifit;

import com.act.datasource.base.entiy.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
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
@TableName("tb_beniftis")
public class BenifitsDO extends BaseDO {

  private String goodIds;

  private String benifitType;

  private String useMode;

  private BigDecimal amount;


}
