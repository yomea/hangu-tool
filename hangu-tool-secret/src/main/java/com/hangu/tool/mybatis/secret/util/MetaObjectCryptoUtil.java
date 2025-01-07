package com.hangu.tool.mybatis.secret.util;

import com.hangu.tool.mybatis.secret.annotated.EnOrDecrypt;
import com.hangu.tool.mybatis.secret.config.DefaultCryptStrategy;
import com.hangu.tool.mybatis.secret.server.DecryptService;
import com.hangu.tool.mybatis.secret.server.EncryptService;
import java.util.Objects;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;

/**
 * @author wuzhenhong
 * @date 2025/1/6 17:27
 */
public class MetaObjectCryptoUtil {

    private static final ObjectFactory OBJECT_FACTORY = new DefaultObjectFactory();
    private static final org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory REFLECTOR_FACTORY = new DefaultReflectorFactory();
    private static final SimpleHashMap<Class<?>, Object> CACHE = new SimpleHashMap<>();

    public static  <T> T getByCache(Class<?> clazz) {
        Object value = CACHE.get(clazz);
        if (Objects.nonNull(value)) {
            return (T) value;
        }
        value = OBJECT_FACTORY.create(clazz);
        CACHE.put(clazz, value);
        return (T) value;
    }

    public static MetaObject forObject(Object object) {
        return MetaObject.forObject(object, OBJECT_FACTORY, OBJECT_WRAPPER_FACTORY,
            REFLECTOR_FACTORY);
    }

    public static String encryptNess(EnOrDecrypt enOrDecryptAnnotation, String value) {
        if (Objects.isNull(value) || Objects.isNull(enOrDecryptAnnotation)) {
            return value;
        }
        if (Objects.isNull(enOrDecryptAnnotation)) {
            return value;
        }
        boolean encrypt = enOrDecryptAnnotation.encrypt();
        if (!encrypt) {
            return value;
        }
        Class<? extends EncryptService>[] encryptServerClass = enOrDecryptAnnotation.encryptClass();
        if (Objects.isNull(encryptServerClass) || encryptServerClass.length == 0) {
            if (Objects.isNull(DefaultCryptStrategy.getDefaultEncrypt())) {
                throw new RuntimeException("默认加密策略不能设置为空！");
            } else {
                encryptServerClass = new Class[]{DefaultCryptStrategy.getDefaultEncrypt()};
            }
        }
        String encryptedValue = value;
        for (Class<? extends EncryptService> encryptServiceClazz : encryptServerClass) {
            EncryptService encryptService = MetaObjectCryptoUtil.getByCache(encryptServiceClazz);
            encryptedValue = encryptService.encrypt(encryptedValue);
        }
        return encryptedValue;
    }

    public static Object decryptNess(EnOrDecrypt deCryptoAnnotation, String value) {
        if (Objects.isNull(value) || Objects.isNull(deCryptoAnnotation)) {
            return value;
        }
        if (Objects.isNull(deCryptoAnnotation)) {
            return value;
        }
        boolean decrypt = deCryptoAnnotation.decrypt();
        if (!decrypt) {
            return value;
        }
        Class<? extends DecryptService>[] decryptServerClass = deCryptoAnnotation.decryptClass();
        if (Objects.isNull(decryptServerClass) || decryptServerClass.length == 0) {
            if (Objects.isNull(DefaultCryptStrategy.getDefaultDecrypt())) {
                throw new RuntimeException("默认解密策略不能设置为空！");
            } else {
                decryptServerClass = new Class[]{DefaultCryptStrategy.getDefaultDecrypt()};
            }
        }
        String decryptedValue = value;
        for (Class<? extends DecryptService> decryptServiceClazz : decryptServerClass) {
            DecryptService decryptService = MetaObjectCryptoUtil.getByCache(decryptServiceClazz);
            decryptedValue = decryptService.decrypt(decryptedValue);
        }

        return decryptedValue;
    }
}
