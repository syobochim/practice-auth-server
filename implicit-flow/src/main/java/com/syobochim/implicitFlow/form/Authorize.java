package com.syobochim.implicitFlow.form;

import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

/**
 * @author syobochim
 */
public class Authorize {
    @NotNull
    public String responseType;

    @AssertTrue
    public boolean isToken() {
        return responseType.equals("token");
    }
}
