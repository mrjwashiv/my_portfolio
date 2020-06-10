package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete-data")
public class DeleteDataServlet extends HttpServlet {
    protected DatastoreService datastore;

    public DeleteDataServlet() {
        super();

        datastore = DatastoreServiceFactory.getDatastoreService();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = -1; 
        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (NumberFormatException e) {
            System.err.println("Could not convert to long: " + id);
        }

        Key commentEntityKey = KeyFactory.createKey("Comment", id);
        datastore.delete(commentEntityKey);
    }
}