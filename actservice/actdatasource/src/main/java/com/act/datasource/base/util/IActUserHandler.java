package com.act.datasource.base.util;

/**
 * Created by steven on 2019/6/25.
 */
public interface IActUserHandler<T> {

  public T getUserData(String userId);

  public String getUserId();

}
