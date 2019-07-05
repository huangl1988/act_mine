package com.act.common.biz.exception;

import lombok.Data;

/**
 * Created by steven on 2019/6/24.
 */
@Data
public class BizException extends RuntimeException {

  public String message;

  private String code;

  public BizException(){
    super();
  }

  public BizException(String code,String message){
    super(message);
  }

  public BizException(String message){
    super(message);
  }


}
