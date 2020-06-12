package com.google.sps.data;

public final class LoginAccount {
    protected final boolean isLoggedIn;
    protected final String loginLink;
    protected final String logoutLink;

    public LoginAccount(boolean isLoggedIn, String loginLink, String logoutLink) {
        this.isLoggedIn = isLoggedIn;
        this.loginLink = loginLink;
        this.logoutLink = logoutLink;
    }
}