package com.hangu.tool.mybatis.secret.interceptor;

import com.hangu.tool.mybatis.secret.util.MetaObjectCryptoUtil;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.reflection.MetaObject;

/**
 * @author wuzhenhong
 * @date 2024/12/27 15:45
 */
public abstract class AbstractInterceptor implements Interceptor {

    protected <T> T getByCache(Class<?> clazz) {
        return MetaObjectCryptoUtil.getByCache(clazz);
    }

    protected MetaObject forObject(Object object) {
        return MetaObjectCryptoUtil.forObject(object);
    }

}
