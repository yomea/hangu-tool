package com.hangu.tool.sensitive.service.impl;

import com.hangu.tool.sensitive.service.DesensitizationService;
import java.util.Objects;

/**
 * @author wuzhenhong
 * @date 2024/12/30 16:17
 */
public class DefaultDesensitizationService implements DesensitizationService {

    @Override
    public String desensitization(String originVal) {
        if(Objects.isNull(originVal) || originVal.trim().isEmpty()) {
            return originVal;
        }
        String val = originVal.trim();
        if(val.length() == 1) {
            return "*";
        }
        if(val.length() == 2) {
            return val.charAt(0) + "*";
        }
        return val.charAt(0) + "*" + val.charAt(val.length() - 1);
    }
}
