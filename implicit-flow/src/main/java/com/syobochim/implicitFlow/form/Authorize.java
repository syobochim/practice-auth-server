package com.syobochim.implicitFlow.form;

import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author syobochim
 */
public class Authorize {
    @NotNull
    public String responseType;

    @NotNull
    public String clientId;

    public String redirectUri;

    @AssertTrue
    public boolean isToken() {
        return responseType.equals("token");
    }

    @AssertTrue
    public boolean isValidRedirectUri() {
        URI uri;
        try {
            uri = new URI(redirectUri);
        } catch (URISyntaxException e) {
            return false;
        }
        return uri.isAbsolute() && uri.getFragment() == null;
    }

}
