package com.google.sps.servlets;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.sps.data.LoginAccount;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected UserService userService;
    protected Gson gson;
    protected static final String LOGIN_REDIRECT = "/index.html";
    protected static final String LOGOUT_REDIRECT = "/index.html";

    public LoginServlet() {
        super();

        userService = UserServiceFactory.getUserService();
        gson = new Gson();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        boolean isLoggedIn = userService.isUserLoggedIn();

        String logoutUrl = userService.createLogoutURL(LOGOUT_REDIRECT);
        String loginUrl = userService.createLoginURL(LOGIN_REDIRECT);

        LoginAccount account = new LoginAccount(isLoggedIn, loginUrl, logoutUrl);

        response.setContentType("application/json");
        response.getWriter().println(gson.toJson(account));
    }
}