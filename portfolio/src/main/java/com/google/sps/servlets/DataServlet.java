// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;
import com.google.sps.data.Comment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
  protected ArrayList<String> serverData = new ArrayList<String>();
  protected DatastoreService datastore;
  protected Gson gson;
  protected UserService user;

  public DataServlet() {
    super();

    datastore = DatastoreServiceFactory.getDatastoreService();
    gson = new Gson();
    user = UserServiceFactory.getUserService();
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Query query = new Query("Comment").addSort("timestamp", SortDirection.DESCENDING);
    PreparedQuery results = datastore.prepare(query);
    List<Entity> entityList = results.asList(FetchOptions.Builder.withLimit(
         getNumofComments(request)));

    ArrayList<Comment> comments = new ArrayList<>();
    for (int i = 0; i < entityList.size(); i++) {
        Entity entity = entityList.get(i);
        long id = entity.getKey().getId();
        String userComment = entity.getProperty("userComment").toString();
        long timestamp = (long) entity.getProperty("timestamp");
        String email = entity.getProperty("userEmail").toString();

        comments.add(new Comment(id, userComment, timestamp, email));
    }

    response.setContentType("application/json");
    String json = convertToJson(comments);
    response.getWriter().println(json);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      String userComment = request.getParameter("user-comment");
      long timestamp = System.currentTimeMillis();
      String userEmail = user.getCurrentUser().getEmail();
      serverData.add(userComment);

      Entity commentEntity = new Entity("Comment");
      commentEntity.setProperty("userComment", userComment);
      commentEntity.setProperty("timestamp", timestamp);
      commentEntity.setProperty("userEmail", userEmail);

      datastore.put(commentEntity);

      response.sendRedirect("/index.html");
  }

  private String convertToJson(ArrayList<Comment> comments) {
      String json = gson.toJson(comments);
      return json;
  }

  private int getNumofComments(HttpServletRequest request) {
      String numOfCommentsString = request.getParameter("numOfComments");

      int numOfComments;
      try {
        numOfComments = Integer.parseInt(numOfCommentsString);
      } catch (NumberFormatException e) {
        System.err.println("Could not convert to int: " + numOfCommentsString);
        return 0;
    }

    return numOfComments;
  }
}