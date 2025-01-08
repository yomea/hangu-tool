package com.hangu.tool.mybatis.secret.aspect;

import com.hangu.tool.mybatis.secret.annotated.EnOrDecrypt;
import com.hangu.tool.mybatis.secret.util.MetaObjectCryptoUtil;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Objects;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author wuzhenhong
 * @date 2025/1/6 17:03
 */
@Aspect
public class EnOrDecryptAspect {

    @Before("@annotation(com.hangu.tool.mybatis.secret.annotated.EnOrDecrypt)")
    public void beforePointcut(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            Parameter[] parameters = method.getParameters();
            if (Objects.nonNull(parameters)) {
                Object[] args = joinPoint.getArgs();
                int index = 0;
                for (Parameter parameter : parameters) {
                    EnOrDecrypt enOrDecrypt = parameter.getAnnotation(EnOrDecrypt.class);
                    Object value = args[index];
                    if (Objects.nonNull(enOrDecrypt) && value instanceof String) {
                        if (Objects.nonNull(value)) {
                            args[index] = MetaObjectCryptoUtil.encryptNess(enOrDecrypt, (String) value);
                        }

                    }
                    index++;
                }
            }
        }
    }
}
