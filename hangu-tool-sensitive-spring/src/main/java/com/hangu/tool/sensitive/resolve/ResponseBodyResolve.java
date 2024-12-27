package com.hangu.tool.sensitive.resolve;

import com.hangu.tool.sensitive.util.JacksonBodyAdviceUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author wuzhenhong
 * @date 2024/12/12 8:22
 */
@ControllerAdvice
public class ResponseBodyResolve implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return Objects.nonNull(returnType.getMethodAnnotation(ResponseBody.class))
            || Objects.nonNull(returnType.getContainingClass().getAnnotation(RestController.class));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
        ServerHttpResponse response) {

        try (OutputStream outputStream = response.getBody()) {
            String json = JacksonBodyAdviceUtil.writeValueAsString(body);
            response.getHeaders().add("Content-Type", selectedContentType.toString());
            // 直接将json字符串写出
            outputStream.write(json.getBytes());
            response.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 返回null，让spring框架不要再处理返回值
        return null;
    }

}