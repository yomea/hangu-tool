package com.hangu.tool.mybatis.secret.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hangu.tool.mybatis.secret.dao.entity.SysUserInfo;

/**
 * <p>
 * 系统-用户信息 服务类
 * </p>
 *
 * @author author
 * @since 2024-05-13
 */
public interface ISysUserInfoService extends IService<SysUserInfo> {

    int updatePasswordByAccount(String password, String account);
    int updatePasswordByAccountSqlSession(String password, String account);
}
