package com.act.common.mapper;

import com.act.common.biz.util.MapperCommon;
import com.act.common.domain.act.TravelActInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by steven on 2019/6/24.
 */
public interface TravelActmapper extends BaseMapper<TravelActInfo> {

  String travelQueryCommon="destination,mem_number memNumber, remarks,join_amount joinAmount,act_content actContent,start_time startTime,end_time endTime,id,title,status,create_user createUser,create_time createTime,update_time updateTime,update_user UpdateUser";

  @Select("<script>select "+travelQueryCommon+" from tb_travel_act t where 1=1 <if test='status!=null'> AND status=#{status} </if> "+ MapperCommon.isActRelated+" <if test='current!=null'> AND id&lt;#{current}</if> <if test='startTime!=null'> AND start_time&lt;=#{startTime}</if> <if test='endTime!=null'> AND end_time&gt;#{endTime} </if>  order by start_time asc,id desc limit 10 </script>")
  List<TravelActInfo> selectListForMy(@Param("current") Long currentId,@Param("startTime") Date startTime, @Param("userId") String userId,@Param("status") Integer status,@Param("endTime")Date endTime);
}
