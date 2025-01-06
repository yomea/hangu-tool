package com.hangu.tool.mybatis.secret.processor;

import com.hangu.tool.mybatis.secret.config.DefaultCryptStrategy;
import com.hangu.tool.mybatis.secret.interceptor.FieldDecryptInterceptor;
import com.hangu.tool.mybatis.secret.interceptor.FieldEncryptAfterInterceptor;
import com.hangu.tool.mybatis.secret.interceptor.FieldEncryptBeforeInterceptor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author wuzhenhong
 * @date 2025/1/4 16:55
 */
public class ConfigInterceptorBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof SqlSessionFactory) {
            // 会进入该方法的入口
            // org.springframework.beans.factory.support.FactoryBeanRegistrySupport.getObjectFromFactoryBean
            DefaultCryptStrategy.loadDefaultCrypto();
            SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) bean;
            Configuration configuration = sqlSessionFactory.getConfiguration();
            FieldEncryptBeforeInterceptor fieldEncryptBeforeInterceptor = new FieldEncryptBeforeInterceptor();
            FieldEncryptAfterInterceptor fieldEncryptAfterInterceptor = new FieldEncryptAfterInterceptor();
            FieldDecryptInterceptor fieldDecryptInterceptor = new FieldDecryptInterceptor();
            configuration.addInterceptor(fieldEncryptBeforeInterceptor);
            configuration.addInterceptor(fieldEncryptAfterInterceptor);
            configuration.addInterceptor(fieldDecryptInterceptor);
        }
        return bean;
    }
}
