package com.act.front.controller.travel;

import com.act.common.biz.config.BizCommonConfig.ACT_STATUS;
import com.act.common.controller.travel.BaseTravelController;
import com.act.common.domain.act.TravelActInfo;
import com.act.datasource.base.util.CurrentUserUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.var;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by steven on 2019/6/25.
 */
@RestController
@RequestMapping("/act/travel/")
@Api("我的旅行")
public class FrontTravelController extends BaseTravelController{

  @ApiOperation(value = "查询关于我正在进行旅行活动")
  @GetMapping("/list/onLine/my")
  public R<List<TravelActInfo>> getTravelActOnLineForMy(Long currentId,@DateTimeFormat(pattern = "yyyymmdd HH:mm:ss") Date startTime){
    var list=travelActmapper.selectListForMy(currentId,startTime, CurrentUserUtil.getUserId(),ACT_STATUS.ONLINE.value,new Date());
    list.stream().forEach(vo->{
      setIsJoin(vo);
    });
    return R.ok(list);
  }

  @ApiOperation(value = "查询关于我的旅行活动")
  @GetMapping("/list/myAll")
  public R<List<TravelActInfo>> getTravelActForMyAll(Long currentId,@DateTimeFormat(pattern = "yyyymmdd HH:mm:ss") Date startTime){
    var list=travelActmapper.selectListForMy(currentId,startTime, CurrentUserUtil.getUserId(),null,null);
    list.stream().forEach(vo->{
      setIsJoin(vo);
    });
    return R.ok(list);
  }

  @ApiOperation(value = "查询关于我的旅行活动")
  @PostMapping("/{id}/join")
  public R join(Long currentId,@DateTimeFormat(pattern = "yyyymmdd HH:mm:ss") Date startTime){
    var list=travelActmapper.selectListForMy(currentId,startTime, CurrentUserUtil.getUserId(),null,null);
    list.stream().forEach(vo->{
      setIsJoin(vo);
    });
    return R.ok(list);
  }


}
