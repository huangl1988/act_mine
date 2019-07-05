package com.act.common.controller.travel;

import com.act.common.biz.config.BizCommonConfig.ACT_STATUS;
import com.act.common.biz.config.BizCommonConfig.BizCode;
import com.act.common.biz.exception.BizException;
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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by steven on 2019/6/24.
 */
@RestController
@RequestMapping("/act/travel")
@Api("公共旅行活动接口")
public class TravelActController extends BaseTravelController{

  @ApiOperation(value = "创建临时旅行活动")
  @PostMapping("/tmp")
  public R<TravelActInfo> createAct(@RequestBody TravelActInfo travelActInfo){
    travelActInfo.setStatus(ACT_STATUS.TMP.value);
    travelActService.insert(travelActInfo);
    return R.ok(travelActInfo);
  }
  @ApiOperation(value = "提交临时旅行活动")
  @PostMapping("/comit/{id}")
  public R commitAct(@PathVariable  Long id){
    QueryWrapper<TravelActInfo> queryWrapper = new QueryWrapper();
    queryWrapper.lambda()
        .eq(TravelActInfo::getCreateUser, CurrentUserUtil.getUserId())
        .eq(TravelActInfo::getId,id)
        .eq(TravelActInfo::getStatus,ACT_STATUS.TMP.value);
    List<TravelActInfo> list=travelActService.queryTravel(queryWrapper);

    if(CollectionUtils.isEmpty(list)){
      throw new BizException(BizCode.DATA_PERMISSION.name());
    }

    travelActService.commitTravelAct(id);
    return R.ok(id);
  }

  @ApiOperation(value = "删除临时旅行活动")
  @DeleteMapping("/tmp/{id}")
  public R deleteAct(@PathVariable  Long id){
    QueryWrapper<TravelActInfo> queryWrapper = new QueryWrapper();
    queryWrapper.lambda()
        .eq(TravelActInfo::getCreateUser, CurrentUserUtil.getUserId())
        .eq(TravelActInfo::getId,id)
        .eq(TravelActInfo::getStatus,ACT_STATUS.TMP.value);
    List<TravelActInfo> list=travelActService.queryTravel(queryWrapper);
    if(CollectionUtils.isEmpty(list)){
      throw new BizException(BizCode.DATA_PERMISSION.name());
    }
    travelActService.deleteAct(id);
    return R.ok(id);
  }

  @ApiOperation(value = "查询单个旅行活动")
  @GetMapping("/{id}")
  public R<TravelActInfo> getTravelAct(@PathVariable  Long id){
    QueryWrapper<TravelActInfo> queryWrapper = new QueryWrapper();
    queryWrapper.lambda()
        //.eq(TravelActInfo::getCreateUser, CurrentUserUtil.getUserId())
        .eq(TravelActInfo::getId,id);
    List<TravelActInfo> list=travelActService.queryTravel(queryWrapper);
    if(CollectionUtils.isEmpty(list)){
      throw new BizException(BizCode.DATA_PERMISSION.name());
    }

    return R.ok(list.get(0));
  }
  @ApiOperation(value = "查询所有线上旅行活动")
  @GetMapping("/list")
  public R<List<TravelActInfo>> getTravelAct(Long currentId,@DateTimeFormat(pattern = "yyyymmdd HH:mm:ss") Date startTime){
    QueryWrapper<TravelActInfo> queryWrapper = new QueryWrapper();
    queryWrapper.lambda()
        .lt(TravelActInfo::getId,currentId)
        .ge(TravelActInfo::getStartTime,startTime)
        .eq(TravelActInfo::getStatus,ACT_STATUS.ONLINE.value)
        .orderByAsc(TravelActInfo::getPublishTime)
        .orderByDesc(TravelActInfo::getId);
    Page<TravelActInfo> page = new Page<>();
    page.setCurrent(1);
    page.setSize(10);

    var queryPage=travelActmapper.selectPage(page,queryWrapper);
    var list=Optional.ofNullable(queryPage.getRecords()).orElse(new ArrayList<TravelActInfo>());
    list.stream().forEach(vo->{
      setIsJoin(vo);
    });
    return R.ok(list);
  }






}
