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

    public LoginServlet() {
        super();

        userService = UserServiceFactory.getUserService();
        gson = new Gson();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        boolean isLoggedIn = userService.isUserLoggedIn();

        String urlToRedirectToAfterUserLogsOut = "/index.html";
        String logoutUrl = userService.createLogoutURL(urlToRedirectToAfterUserLogsOut);

        String urlToRedirectToAfterUserLogsIn = "/index.html";
        String loginUrl = userService.createLoginURL(urlToRedirectToAfterUserLogsIn);

        LoginAccount account = new LoginAccount(isLoggedIn, loginUrl, logoutUrl);

        if (isLoggedIn) {
            String userEmail = userService.getCurrentUser().getEmail();

            // response.getWriter().println("<p>Hello " + userEmail + "!</p>");
            // response.getWriter().println("<p>Logout <a href=\"" + logoutUrl + "\">here</a>.</p>");
        } else {
            // response.getWriter().println("<p>Hello stranger.</p>");
            // response.getWriter().println("<p>Login <a href=\"" + loginUrl + "\">here</a>.</p>");
        }

        response.setContentType("application/json");
        response.getWriter().println(gson.toJson(account));
    }
}