package com.hangu.tool.secret.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hangu.tool.mybatis.secret.annotated.CryptoNecessary;
import com.hangu.tool.mybatis.secret.annotated.EnOrDecrypt;
import com.hangu.tool.secret.dao.entity.SysUserInfo;
import com.hangu.tool.secret.dao.mapper.SysUserInfoMapper;
import com.hangu.tool.secret.service.ISysUserInfoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统-用户信息 服务实现类
 * </p>
 *
 * @author author
 * @since 2024-05-13
 */
@CryptoNecessary
@Service
public class SysUserInfoServiceImpl extends ServiceImpl<SysUserInfoMapper, SysUserInfo> implements ISysUserInfoService {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public int updatePasswordByAccount(String password, String account) {
        return super.baseMapper.updatePasswordByAccount(password, account);
    }

    @Override
    public int updatePasswordByAccountSqlSession(@EnOrDecrypt String password, String account) {
        Map<String, Object> param = new HashMap<>();
        param.put("password", password);
        param.put("account", account);
        int row = sqlSessionFactory.openSession().update(SysUserInfoMapper.class.getName() + ".updatePasswordByAccount",
            param);
        return row;
    }

    @Override
    public List<SysUserInfo> queryByPassword(SysUserInfo query) {
        return super.baseMapper.queryByPassword(query);
    }

    @Override
    public int deleteByPassword(String password) {
        return super.baseMapper.deleteByPassword(password);
    }

    @Override
    public int updateByPassword(String newPassword, String oldPassword) {
        return super.baseMapper.updateByPassword(newPassword, oldPassword);
    }

    @Override
    public int updateEntityByPassword(SysUserInfo update, String oldPassword) {
        return super.baseMapper.updateEntityByPassword(update, oldPassword);
    }
}
