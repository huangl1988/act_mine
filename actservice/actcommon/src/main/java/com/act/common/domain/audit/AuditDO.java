package com.act.common.domain.audit;

import com.act.datasource.base.entiy.BaseDO;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by steven on 2019/6/26.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AuditDO extends BaseDO {

  private Integer status;

  private Date auditTime;

  private Long referId;

  private String referType;

}
