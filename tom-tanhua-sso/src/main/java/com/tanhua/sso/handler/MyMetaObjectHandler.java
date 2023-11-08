package com.tanhua.sso.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Object created = getFieldValByName("created", metaObject);

        Date now = new Date();

        if (null == created) {
            //字段为空，可以进行填充
            setFieldValByName("created", now, metaObject);
        }
        Object updated = getFieldValByName("updated", metaObject);
        if (null == updated) {
            //字段为空，可以进行填充
            setFieldValByName("updated", now, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) { //更新数据时，直接更新字段
        setFieldValByName("updated", new Date(), metaObject);
    }

}