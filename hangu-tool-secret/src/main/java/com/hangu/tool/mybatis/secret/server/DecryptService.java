package com.hangu.tool.mybatis.secret.server;

/**
 * 解密服务
 *
 * @author wuzhenhong
 * @date 2024/12/27 9:32
 */
public interface DecryptService {

    /**
     * @param cryptVal 加密值
     * @return 解密值
     */
    String decrypt(String cryptVal);
}
