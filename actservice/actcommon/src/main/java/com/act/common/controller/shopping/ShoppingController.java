package com.act.common.controller.shopping;

import com.act.common.domain.act.ShoppingActDO;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by steven on 2019/7/4.
 */
@RestController
@RequestMapping("/act/shopping")
public class ShoppingController extends BaseShoppingController{



  @PostMapping
  public R<ShoppingActDO> createAct(@RequestBody ShoppingActDO shoppingActDO){

  }

}
