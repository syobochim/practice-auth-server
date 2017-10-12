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
    private String response_type;

    @NotNull
    private String client_id;

    @NotNull
    private String redirect_uri;

    private String scope;

    private String state;

    private String nonce;

    public String getResponseType() {
        return response_type;
    }

    public void setResponse_type(String response_type) {
        this.response_type = response_type;
    }

    public String getClientTd() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getRedirectUri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
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
        return response_type == null || response_type.equals("id_token") || response_type.equals("id_token token");
    }

    @AssertTrue
    public boolean isValidRedirectUri() {
        if (redirect_uri == null) {
            return true;
        }
        try {
            URI uri = new URI(redirect_uri.split(" ")[0]);
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
