package ai.yue.library.data.mp.handler;

import ai.yue.library.base.util.DateUtils;
import ai.yue.library.data.mp.entity.BaseEntity;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Date;

public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final Logger log = LoggerFactory.getLogger(MyMetaObjectHandler.class);

    private static final String VERSION = "version";

    @Override
    public void insertFill(MetaObject metaObject) {
        if(BaseEntity.class.isAssignableFrom(metaObject.getOriginalObject().getClass())){
            Date now = DateUtils.date().toJdkDate();
            this.strictInsertFill(metaObject, "createTime", Date.class, now); // 起始版本 3.3.0(推荐使用)
            this.strictInsertFill(metaObject, "updateTime", Date.class, now); // 起始版本 3.3.0(推荐使用)
            this.strictInsertFill(metaObject, "version", Integer.class, 1); // 起始版本 3.3.0(推荐使用)
            if (metaObject.getObjectWrapper().hasSetter(VERSION)) {
                this.setFieldValByName(VERSION, 1,metaObject);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if(BaseEntity.class.isAssignableFrom(metaObject.getOriginalObject().getClass())){
            this.setFieldValByName("updateTime", DateUtils.date().toJdkDate(),metaObject); // 通用填充方法
        }
    }
}