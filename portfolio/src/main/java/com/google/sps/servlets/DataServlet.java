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

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.google.gson.Gson;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
  private ArrayList<String> serverData = new ArrayList<String>();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    String json = convertToJson(serverData);
    response.getWriter().println(json);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      String userComment = getUserComment(request);
      serverData.add(userComment);

      response.sendRedirect("/data");
  }

  private String convertToJson(ArrayList<String> serverData) {
      Gson gson = new Gson();
      String json = gson.toJson(serverData);
      return json;
  }

  private String getUserComment(HttpServletRequest request) {
      String userComment = request.getParameter("user-comment");
      return userComment;
  }
}