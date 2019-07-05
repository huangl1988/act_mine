package com.act.common.biz.util;

/**
 * Created by steven on 2019/6/25.
 */
public interface MapperCommon {
    public final static String isActRelated="and (exists(SELECT 1 from tb_act_member where act_id=t.id and user_id=#{userId} and act_type='travel') or exists (select 1 from tb_act_noticer where act_id=t.id and user_id=#{userId} and act_type='travel')) ";
}
