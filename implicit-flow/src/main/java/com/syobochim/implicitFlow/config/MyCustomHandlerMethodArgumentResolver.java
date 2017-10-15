package com.syobochim.implicitFlow.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syobochim.implicitFlow.form.Authorize;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Collection;

/**
 * @author syobochim
 */
public class MyCustomHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private ObjectMapper objectMapper;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
//        return parameter.getParameterType().equals(Authorize.class);
        return true;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//        Collection<String[]> values = webRequest.getParameterMap().values();
//        return objectMapper.convertValue(values, Authorize.class);
        Authorize authorize = new Authorize();
        authorize.setClientId("hogehoge");
        return authorize;
    }
}
