#!/bin/bash

set -e

curl -X POST http://localhost:8080/api/articles/0000000000001/paragraphs/0000000000036 \
  -H "Content-Type: application/json" \
  -d '{
    "articleId": "0000000000001",
    "paragraphId": "0000000000036",
    "text": "Updated explanation about event replay in event-sourced systems. One more time"
  }'
