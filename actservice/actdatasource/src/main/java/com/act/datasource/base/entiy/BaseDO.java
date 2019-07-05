package com.act.datasource.base.entiy;

import com.act.datasource.base.util.CurrentUserUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;

/**
 * Created by steven on 2019/6/24.
 */
@Data
public abstract class BaseDO {
  private String createUser;

  @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
  private Date createTime;
  @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
  private Date updateTime;

  private String updateUser;

  public void initDo(){
    createUser= CurrentUserUtil.getUserId();
    updateUser=createUser;
    createTime=new Date();
    updateTime=new Date();
  }

  private Long id;

}
