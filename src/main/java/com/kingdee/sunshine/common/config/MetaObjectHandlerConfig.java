package com.kingdee.sunshine.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.kingdee.sunshine.common.utils.Constant;
import com.kingdee.sunshine.service.HttpSessionService;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;


/**
 * mybatis plus 默认值配置
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@Component
public class MetaObjectHandlerConfig implements MetaObjectHandler {

    @Lazy
    @Resource
    HttpSessionService httpSessionService;

    @Override
    public void insertFill(MetaObject metaObject) {
        Date currentDate = new Date();
        String[] setterNames = metaObject.getSetterNames();
        HashSet<String> setterNameSet = new HashSet<>(Arrays.asList(setterNames));
        if (setterNameSet.contains("deleted")) {
            //默认未删除
            setFieldValByName("deleted", Constant.DATA_NOT_DELETED, metaObject);
        }
        if (setterNameSet.contains("createTime")) {
            //创建时间默认当前时间
            setFieldValByName("createTime", currentDate, metaObject);
        }
        if (setterNameSet.contains("createDate")) {
            //创建时间默认当前时间
            setFieldValByName("createDate", currentDate, metaObject);
        }
        if (setterNameSet.contains("createId")) {
            //创建时间默认当前时间
            setFieldValByName("createId", httpSessionService.getCurrentUserId(), metaObject);
        }
        if (setterNameSet.contains("updateId")) {
            //创建时间默认当前时间
            setFieldValByName("updateId", httpSessionService.getCurrentUserId(), metaObject);
        }
        if (setterNameSet.contains("updateTime")) {
            //创建时间默认当前时间
            setFieldValByName("updateTime", currentDate, metaObject);
        }
        if (setterNameSet.contains("updateDate")) {
            //创建时间默认当前时间
            setFieldValByName("updateDate", currentDate, metaObject);
        }


    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date currentDate = new Date();
        String[] setterNames = metaObject.getSetterNames();
        HashSet<String> setterNameSet = new HashSet<>(Arrays.asList(setterNames));
        if (setterNameSet.contains("updateTime")) {
            //创建时间默认当前时间
            setFieldValByName("updateTime", currentDate, metaObject);
        }
        if (setterNameSet.contains("updateDate")) {
            //创建时间默认当前时间
            setFieldValByName("updateDate", currentDate, metaObject);
        }
        if (setterNameSet.contains("updateId")) {
            //创建时间默认当前时间
            setFieldValByName("updateId", httpSessionService.getCurrentUserId(), metaObject);
        }
    }
}