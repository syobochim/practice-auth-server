package com.syobochim.implicitFlow.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.syobochim.implicitFlow.form.Authorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.*;
import java.util.Map;
import java.util.Set;

/**
 * @author syobochim
 */
@Controller
public class AuthenticationController {

    @GetMapping(value = "/authorize")
    @ResponseBody
    String goToApprovalView(@RequestParam Map<String, String> queryParameters) {
        Authorize authorize = getObjectMapper().convertValue(queryParameters, Authorize.class);
        validate(authorize);
        return authorize.responseType;
    }

    private ObjectMapper getObjectMapper() {
        return new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    private void validate(Object form) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(form);
        if (!violations.isEmpty()) {
            // TODO : ちゃんとvalidationErrorをハンドリングする
            throw new ValidationException(violations.toString());
        }
    }

}
