package com.hangu.tool.mybatis.secret.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hangu.tool.mybatis.secret.annotated.EnOrDecrypt;
import com.hangu.tool.mybatis.secret.dao.entity.SysUserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 系统-用户信息 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-05-13
 */
public interface SysUserInfoMapper extends BaseMapper<SysUserInfo> {

    int updatePasswordByAccount(@EnOrDecrypt @Param("password") String password, @Param("account") String account);
}
