package com.act.common.vo;

import com.act.common.domain.act.ShoppingActDO;
import com.act.common.domain.actscope.ShoppingActScope;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by steven on 2019/7/5.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReqShoppingActVo {
  @NotNull
  private ShoppingActDO shoppingActDO;

  private List<ShoppingActScope> shoppingActScope;


}
