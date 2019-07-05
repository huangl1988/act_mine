package com.act.common.biz.util;

import com.act.datasource.base.util.IActUserHandler;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by steven on 2019/6/25.
 */
@Component
@Builder
public class DemoUserHandler implements IActUserHandler<Map<String,String>>{

  @Override
  public Map<String, String> getUserData(String userId) {
    Map<String,String> map = new HashMap<>();
    return map;
  }

  @Override
  public String getUserId() {
    return "1l";
  }
}
