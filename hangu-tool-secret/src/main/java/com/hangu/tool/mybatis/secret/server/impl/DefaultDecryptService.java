package com.hangu.tool.mybatis.secret.server.impl;

import com.hangu.tool.mybatis.secret.server.DecryptService;
import com.hangu.tool.mybatis.secret.util.AESUtil;
import java.security.GeneralSecurityException;

/**
 * @author wuzhenhong
 * @date 2024/12/30 11:16
 */
public class DefaultDecryptService implements DecryptService {

    @Override
    public String decrypt(String cryptVal) {
        try {
            return AESUtil.decryptECB(AESUtil.key128, cryptVal);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }
}
