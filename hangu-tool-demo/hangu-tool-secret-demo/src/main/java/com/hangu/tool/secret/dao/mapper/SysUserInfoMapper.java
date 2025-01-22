package com.hangu.tool.secret.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hangu.tool.mybatis.secret.annotated.EnOrDecrypt;
import com.hangu.tool.secret.dao.entity.SysUserInfo;
import java.util.List;
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

    List<SysUserInfo> queryByPassword(SysUserInfo query);

    int deleteByPassword(@EnOrDecrypt @Param("password") String password);

    int updateByPassword(@EnOrDecrypt @Param("newPassword") String newPassword,
        @EnOrDecrypt @Param("oldPassword") String oldPassword);

    int updateEntityByPassword(@Param("update") SysUserInfo update,
        @EnOrDecrypt @Param("oldPassword") String oldPassword);
}
