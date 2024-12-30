package com.hangu.tool.mybatis.secret.config;

import com.hangu.tool.mybatis.secret.server.DecryptService;
import com.hangu.tool.mybatis.secret.server.EncryptService;
import com.hangu.tool.mybatis.secret.server.impl.DefaultDecryptService;
import com.hangu.tool.mybatis.secret.server.impl.DefaultEncryptService;
import java.util.Objects;

/**
 * @author wuzhenhong
 * @date 2024/12/30 13:57
 */
public class DefaultCryptStrategy {

    private static Class<? extends EncryptService> defaultEncrypt = DefaultEncryptService.class;
    private static Class<? extends DecryptService> defaultDecrypt = DefaultDecryptService.class;

    public static Class<? extends EncryptService> getDefaultEncrypt() {
        return defaultEncrypt;
    }

    public static void setDefaultEncrypt(
        Class<? extends EncryptService> defaultEncrypt) {
        if(Objects.isNull(defaultEncrypt)) {
            throw new RuntimeException("默认加密策略不能为空！");
        }
        DefaultCryptStrategy.defaultEncrypt = defaultEncrypt;
    }

    public static Class<? extends DecryptService> getDefaultDecrypt() {
        return defaultDecrypt;
    }

    public static void setDefaultDecrypt(
        Class<? extends DecryptService> defaultDecrypt) {
        if(Objects.isNull(defaultDecrypt)) {
            throw new RuntimeException("默认解密策略不能为空！");
        }
        DefaultCryptStrategy.defaultDecrypt = defaultDecrypt;
    }
}
