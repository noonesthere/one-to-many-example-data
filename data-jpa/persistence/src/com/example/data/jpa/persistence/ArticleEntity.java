package com.example.data.jpa.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "article")
public class ArticleEntity { // int 32, int 32, int 32, date 4, datetime 8, varchar(100) 0-255 50
  // int (static length) varchar (variable length) datetime(static )....
  private String id;
  private String title;
  private String content;
  private List<CommentEntity> comments;
  private Long version;
  private Instant createdAt;
  private Instant updatedAt;
  private Instant deletedAt;
}
