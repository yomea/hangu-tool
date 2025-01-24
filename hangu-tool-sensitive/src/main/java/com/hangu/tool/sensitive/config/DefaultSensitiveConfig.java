package com.hangu.tool.sensitive.config;

import com.hangu.tool.sensitive.service.DesensitizationService;
import com.hangu.tool.sensitive.service.EncryptService;
import com.hangu.tool.sensitive.service.impl.DefaultDesensitizationService;
import com.hangu.tool.sensitive.service.impl.DefaultEncryptService;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

/**
 * @author wuzhenhong
 * @date 2024/12/30 13:57
 */
public class DefaultSensitiveConfig {

    private static final String CONFIG_NAME = "default_sensitive.properties";
    private static final String DEFAULT_ENCRYPT_NAME = "defaultEncryptService";
    private static final String DEFAULT_DESENSITIZATION_NAME = "defaultDesensitizationService";

    private static Class<? extends EncryptService> defaultEncrypt = DefaultEncryptService.class;
    private static Class<? extends DesensitizationService> defaultDesensitization = DefaultDesensitizationService.class;

    public static Class<? extends EncryptService> getDefaultEncrypt() {
        return defaultEncrypt;
    }

    public static Class<? extends DesensitizationService> getDefaultDesensitization() {
        return defaultDesensitization;
    }

    public static void loadDefaultSensitive() {
        try {
            URL url = DefaultSensitiveConfig.class.getClassLoader().getResource(CONFIG_NAME);
            if (Objects.nonNull(url)) {
                Properties properties = new Properties();
                try (InputStream inputStream = url.openStream()) {
                    properties.load(inputStream);
                }
                String encryptClassName = properties.getProperty(DEFAULT_ENCRYPT_NAME);
                if (Objects.nonNull(encryptClassName) && !encryptClassName.trim().isEmpty()) {
                    try {
                        Class<?> defaultEntryClass = Class.forName(encryptClassName);
                        if (!EncryptService.class.isAssignableFrom(defaultEntryClass)) {
                            throw new RuntimeException(String.format("className -> %s 未实现%s", encryptClassName,
                                EncryptService.class.getName()));
                        }
                        defaultEncrypt = (Class<? extends EncryptService>) defaultEntryClass;
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                String sensitiveClassName = properties.getProperty(DEFAULT_DESENSITIZATION_NAME);
                if (Objects.nonNull(sensitiveClassName) && !sensitiveClassName.trim().isEmpty()) {
                    try {
                        Class<?> defaultSensitiveClass = Class.forName(sensitiveClassName);
                        if (!DesensitizationService.class.isAssignableFrom(defaultSensitiveClass)) {
                            throw new RuntimeException(String.format("className -> %s 未实现%s", sensitiveClassName,
                                DesensitizationService.class.getName()));
                        }
                        defaultDesensitization = (Class<? extends DesensitizationService>) defaultSensitiveClass;
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
