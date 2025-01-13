package com.hangu.tool.fastjson.entity;

import com.hangu.tool.fastjson.processor.MoneyNullProcessor;
import com.hangu.tool.serialize.fastjson.annotated.NullJSONField;
import java.math.BigDecimal;
import lombok.Data;

/**
 * @author wuzhenhong
 * @date 2025/1/13 16:26
 */
@Data
public class User {

    private String name;

    @NullJSONField(nullsUsing = MoneyNullProcessor.class)
    private BigDecimal money;
}
