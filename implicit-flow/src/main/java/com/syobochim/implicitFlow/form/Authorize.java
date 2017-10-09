package com.syobochim.implicitFlow.form;

import com.syobochim.implicitFlow.domain.Scope;

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

    public String scope;

    public String state;

    @NotNull
    public String nonce;

    @AssertTrue
    public boolean isToken() {
        return responseType.equals("token");
    }

    @AssertTrue
    public boolean isValidRedirectUri() {
        if (redirectUri == null) {
            return true;
        }

        URI uri;
        try {
            uri = new URI(redirectUri);
        } catch (URISyntaxException e) {
            return false;
        }
        return uri.isAbsolute() && uri.getFragment() == null;
    }

    @AssertTrue
    public boolean isValidScope() {
        if (scope == null) {
            return true;
        }

        String[] scopes = scope.split(" ");
        if (!(scopes.length > 0 && scopes[0].equals("openid"))) {
            return false;
        }

        for (String scope : scopes) {
            if (!Scope.contains(scope)) {
                return false;
            }
        }
        return true;
    }

}
