package com.google.sps.data;

public final class Comment {

  private final long id;
  private final String userComment;
  private final long timestamp;
  private final String userEmail;

  public Comment(long id, String userComment, long timestamp, 
                 String userEmail) {
    this.id = id;
    this.userComment = userComment;
    this.timestamp = timestamp;
    this.userEmail = userEmail;
  }
}