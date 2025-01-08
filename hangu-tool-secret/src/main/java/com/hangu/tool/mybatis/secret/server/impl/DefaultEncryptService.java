package com.hangu.tool.mybatis.secret.server.impl;

import com.hangu.tool.common.util.AESUtil;
import com.hangu.tool.mybatis.secret.server.EncryptService;
import java.security.GeneralSecurityException;

/**
 * @author wuzhenhong
 * @date 2024/12/30 11:16
 */
public class DefaultEncryptService implements EncryptService {

    @Override
    public String encrypt(String originVal) {
        try {
            return AESUtil.encryptECB(AESUtil.key128, originVal);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }
}
