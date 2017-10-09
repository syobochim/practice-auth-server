package com.syobochim.implicitFlow.domain;

/**
 * @author syobochim
 */
public enum Scope {
    openid, profile, email, address, phone;

    public static boolean contains(String s) {
        for (Scope scope : values()) {
            if (scope.name().equals(s)) {
                return true;
            }
        }
        return false;
    }

}
