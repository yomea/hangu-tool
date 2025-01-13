package com.hangu.tool.sensitive2.vo;

import com.hangu.tool.sensitive.annotated.Sensitive;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author wuzhenhong
 * @date 2025/1/11 15:36
 */
@Data
public class UserVO implements Serializable {

    private String userName;

    @Sensitive
    private String mobile;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
