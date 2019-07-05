package com.act.common.domain.act;

import com.act.datasource.base.entiy.BaseDO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by steven on 2019/6/24.
 */
@Data
public abstract class BaseActInfoDO extends BaseDO{

  private String actContent;
  @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
  private Date startTime;
  @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
  private Date endTime;

  private Integer status;

  private String title;
  @JsonIgnore
  private Date publishTime;

  public abstract String getActType();

  public abstract Class getSingleMapper();

}
