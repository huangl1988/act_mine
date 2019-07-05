package com.act.datasource.base.util;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by steven on 2019/6/25.
 */
@Component
public class CurrentUserUtil {

  private static IActUserHandler userHandler;

  @Autowired
  IActUserHandler iActUserHandler;

  @PostConstruct
  public void affterInit(){
    userHandler=iActUserHandler;
  }

  public static String getUserId(){
    return userHandler.getUserId();
  }

  public static Object getUserId(String userId){
    return userHandler.getUserData(userId);
  }

}
