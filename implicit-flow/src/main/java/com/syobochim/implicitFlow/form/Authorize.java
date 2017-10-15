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
    private String responseType;

    @NotNull
    private String clientId;

    @NotNull
    private String redirectUri;

    private String scope;

    private String state;

    private String nonce;

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getClientTd() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    @AssertTrue
    public boolean isToken() {
        return responseType == null || responseType.equals("id_token") || responseType.equals("id_token token");
    }

    @AssertTrue
    public boolean isValidRedirectUri() {
        if (redirectUri == null) {
            return true;
        }
        try {
            URI uri = new URI(redirectUri.split(" ")[0]);
            return uri.isAbsolute() && uri.getFragment() == null;
        } catch (URISyntaxException e) {
            return false;
        }
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
