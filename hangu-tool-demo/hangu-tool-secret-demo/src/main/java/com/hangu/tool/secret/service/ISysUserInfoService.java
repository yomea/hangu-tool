package com.hangu.tool.secret.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hangu.tool.secret.dao.entity.SysUserInfo;
import java.util.List;

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

    List<SysUserInfo> queryByPassword(SysUserInfo query);

    int deleteByPassword(String password);

    int updateByPassword(String newPassword, String oldPassword);

    int updateEntityByPassword(SysUserInfo update, String oldPassword);
}
