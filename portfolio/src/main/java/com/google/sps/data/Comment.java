package com.google.sps.data;

public final class Comment {

  protected final long id;
  protected final String userComment;
  protected final long timestamp;
  protected final String userEmail;
  protected final String imageUrl;

  public Comment(long id, String userComment, long timestamp, 
                 String userEmail, String imageUrl) {
    this.id = id;
    this.userComment = userComment;
    this.timestamp = timestamp;
    this.userEmail = userEmail;
    this.imageUrl = imageUrl;
  }
}