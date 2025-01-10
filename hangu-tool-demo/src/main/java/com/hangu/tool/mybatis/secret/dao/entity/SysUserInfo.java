package com.hangu.tool.mybatis.secret.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hangu.tool.mybatis.secret.annotated.EnOrDecrypt;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统-用户信息
 * </p>
 *
 * @author author
 * @since 2024-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user_info")
public class SysUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String userName;

    private String account;

    @EnOrDecrypt(encrypt = true, decrypt = true)
    private String password;

    private String avatar;

    private LocalDateTime createAt;

    private Integer version;

    private LocalDateTime updateAt;

    private Integer status;


}
