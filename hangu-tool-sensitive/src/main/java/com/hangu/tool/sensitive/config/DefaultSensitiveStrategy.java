package com.hangu.tool.sensitive.config;

import com.hangu.tool.sensitive.service.DesensitizationService;
import com.hangu.tool.sensitive.service.EncryptService;
import com.hangu.tool.sensitive.service.impl.DefaultDesensitizationService;
import com.hangu.tool.sensitive.service.impl.DefaultEncryptService;
import java.util.Objects;

/**
 * @author wuzhenhong
 * @date 2024/12/30 13:57
 */
public class DefaultSensitiveStrategy {

    private static Class<? extends EncryptService> defaultEncrypt = DefaultEncryptService.class;
    private static Class<? extends DesensitizationService> defaultDesensitization = DefaultDesensitizationService.class;

    public static Class<? extends EncryptService> getDefaultEncrypt() {
        return defaultEncrypt;
    }

    public static void setDefaultEncrypt(
        Class<? extends EncryptService> defaultEncrypt) {
        if (Objects.isNull(defaultEncrypt)) {
            throw new RuntimeException("默认加密策略不能为空！");
        }
        DefaultSensitiveStrategy.defaultEncrypt = defaultEncrypt;
    }

    public static Class<? extends DesensitizationService> getDefaultDesensitization() {
        return defaultDesensitization;
    }

    public static void setDefaultDesensitization(
        Class<? extends DesensitizationService> defaultDesensitization) {
        if (Objects.isNull(defaultDesensitization)) {
            throw new RuntimeException("默认脱敏策略不能为空！");
        }
        DefaultSensitiveStrategy.defaultDesensitization = defaultDesensitization;
    }
}
