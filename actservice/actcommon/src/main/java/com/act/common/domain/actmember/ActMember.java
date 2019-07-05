package com.act.common.domain.actmember;

import com.act.datasource.base.entiy.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by steven on 2019/6/25.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper =true)
@TableName("tb_act_members")
public class ActMember extends BaseDO{

  private Long actId;

  private String userId;

  private String actType;

}
