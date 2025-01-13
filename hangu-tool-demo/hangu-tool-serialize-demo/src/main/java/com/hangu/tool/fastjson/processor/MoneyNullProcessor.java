package com.hangu.tool.fastjson.processor;

import java.math.BigDecimal;
import java.util.function.Supplier;

/**
 * @author wuzhenhong
 * @date 2025/1/13 16:28
 */
public class MoneyNullProcessor implements Supplier<BigDecimal> {

    @Override
    public BigDecimal get() {
        return new BigDecimal("0.00");
    }
}
