package com.syobochim.implicitFlow.controller;

import com.syobochim.implicitFlow.form.Authorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author syobochim
 */
@RestController
public class AuthenticationController {

    @GetMapping(value = "/authorize")
    String goToApprovalView(@Validated @ModelAttribute Authorize authorize) {
        return authorize.getRedirectUri();
    }

}
