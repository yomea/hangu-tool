package com.hangu.tool.fastjson.test;

import com.alibaba.fastjson.JSONObject;
import com.hangu.tool.fastjson.entity.User;
import com.hangu.tool.serialize.fastjson.filter.NullSerializeValueFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author wuzhenhong
 * @date 2025/1/13 16:33
 */
@RunWith(JUnit4.class)
public class NullProcessorTest {

    @Test
    public void nullDeal() {
        User user = new User();
        user.setName("小红");
        user.setMoney(null);
        NullSerializeValueFilter filter = new NullSerializeValueFilter();
        System.out.println(JSONObject.toJSONString(user, filter));
        System.out.println(JSONObject.toJSONString(user, filter));
    }
}
