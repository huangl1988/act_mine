package com.act.common.biz.config;

/**
 * Created by steven on 2019/6/25.
 */
public class BizCommonConfig {

  public enum ACT_STATUS
  {
    /**
     * 临时保存
     */
    TMP(99),
    /**
     * 初始化
     */
    INIT(0),
    /**
     * 待审核
     */
    AUDIT(1),
    /**
     * 上线
     */
    ONLINE(2),
    /**
     * 拒绝
     */
    REJUECT(3),
    /**
     * 正常下线
     */
    NOFFLINE(4),
    /**
     * 后台强制下线
     */
    FOFFLINE(5);


    ACT_STATUS(Integer value){
      this.value=value;
    }
    public Integer value;
  }

  public enum  ACT_MEMBER_STATUS{
    CREATOR(1),
    JOINER(2),
    NOTICER(3),
    NORELATION(4);
    public Integer value;
    ACT_MEMBER_STATUS(Integer v){
      this.value=v;
    }


  }



  public enum BizCode{

    DATA_PERMISSION,
    DATA_ERROR,


  }

}
