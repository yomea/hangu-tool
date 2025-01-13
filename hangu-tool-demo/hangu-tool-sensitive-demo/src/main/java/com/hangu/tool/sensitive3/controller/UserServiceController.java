package com.hangu.tool.sensitive3.controller;

import com.hangu.tool.sensitive3.vo.UserVO;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuzhenhong
 * @date 2025/1/11 15:35
 */
@RestController
public class UserServiceController {

    @RequestMapping("/user")
    public UserVO getUser() {

        UserVO userVO = new UserVO();
        userVO.setUserName("测试");
        userVO.setMobile("15179879654");
        userVO.setCreateAt(LocalDateTime.now());
        userVO.setUpdateAt(LocalDateTime.now());

        return userVO;
    }
}
