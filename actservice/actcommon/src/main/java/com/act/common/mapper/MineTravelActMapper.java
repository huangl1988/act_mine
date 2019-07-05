package com.act.common.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * Created by steven on 2019/6/25.
 */
public interface MineTravelActMapper {
  @Update("update tb_travel_act set status=#{toStatus},update_time=now() where id=#{id} and status=#{fromStatus}")
  int updateTravelStatus(@Param("id") Long id,@Param("fromStatus") int fromStatus,@Param("toStatus") int toStatus);
  @Delete("delete from tb_travel_act where id=#{id} and status=#{fromStatus}")
  int deleteAct(@Param("id") Long id,@Param("fromStatus") int fromStatus);
}
