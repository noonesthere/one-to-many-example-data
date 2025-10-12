INSERT INTO CATEGORY (ID, NAME, UPDATED_AT, DELETED_AT, VERSION)
VALUES (8723654912736489123, 'Books', TIMESTAMP '2025-10-06 10:00:00', NULL, 0);

INSERT INTO CATEGORY (ID, NAME, UPDATED_AT, DELETED_AT, VERSION)
VALUES (8723654912736489124, 'Electronics', TIMESTAMP '2025-10-06 10:05:00', NULL, 0);

INSERT INTO CATEGORY (ID, NAME, UPDATED_AT, DELETED_AT, VERSION)
VALUES (8723654912736489125, 'Groceries', TIMESTAMP '2025-10-06 10:10:00', NULL, 0);

INSERT INTO CATEGORY_COUNTER (CATEGORY_ID, COUNTER)
VALUES (8723654912736489123, 2);

INSERT INTO CATEGORY_COUNTER (CATEGORY_ID, COUNTER)
VALUES (8723654912736489124, 1);

INSERT INTO CATEGORY_COUNTER (CATEGORY_ID, COUNTER)
VALUES (8723654912736489125, 0);

-- Insert sample Articles
INSERT INTO ARTICLE (ID, TITLE, CATEGORY_ID, RATING, VOTE_COUNT,  PUBLISHED_AT, UPDATED_AT, DELETED_AT, STATUS, VERSION)
VALUES
  (1, 'Understanding Event Sourcing', 8723654912736489124, 4.7, 10,  TIMESTAMP '2025-10-06 10:00:00', TIMESTAMP '2025-10-06 10:00:00', NULL, 1, 0),
  (2, 'Spring Data JDBC Deep Dive', 8723654912736489123, 4.5, 5, TIMESTAMP '2025-10-06 11:00:00', TIMESTAMP '2025-10-06 11:00:00', NULL, 1, 0),
  (3, 'Soft Deletes and Versioning Strategies', 8723654912736489123, 4.8, 8, TIMESTAMP '2025-10-06 12:00:00', TIMESTAMP '2025-10-06 12:00:00', NULL, 1, 0);

-- Insert sample Paragraphs (multiple per Article)
INSERT INTO PARAGRAPH (ID, ARTICLE_ID, TEXT, VERSION)
VALUES
  -- Article 1 paragraphs
  (101, 1, 'Event sourcing captures every state change as an immutable event.', 0),
  (102, 1, 'This allows rebuilding application state by replaying events.', 0),
  (103, 1, 'Spring Modulith simplifies this with built-in event publication support.', 0),

  -- Article 2 paragraphs
  (201, 2, 'Spring Data JDBC promotes simple, aggregate-oriented persistence.', 0),
  (202, 2, 'It avoids lazy loading and proxies, making data access more predictable.', 0),

  -- Article 3 paragraphs
  (301, 3, 'Soft deletes mark entities as deleted without physical removal.', 0),
  (302, 3, 'Version columns help manage concurrent updates safely.', 0),
  (303, 3, 'Combining both ensures auditability and consistency.', 0);
