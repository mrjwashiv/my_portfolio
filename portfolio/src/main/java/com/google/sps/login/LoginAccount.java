package com.google.sps.data;

public final class LoginAccount {
    //private final String username;
    //protected final String email;
    protected final boolean isLoggedIn;
    protected final String loginLink;
    protected final String logoutLink;

    public LoginAccount(boolean isLoggedIn, String loginLink, String logoutLink) {
        //this.username = username;
        //this.email = email;
        this.isLoggedIn = isLoggedIn;
        this.loginLink = loginLink;
        this.logoutLink = logoutLink;
    }
}