package com.example.data.jpa.persistence;

import java.time.Instant;

public class CommentEntity {
  private String id;
  private String articleId;
  private String text;
  private Instant createdAt;
  private Instant updatedAt;
  private Instant deletedAt;
}
