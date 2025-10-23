#!/bin/bash

set -e

curl -X POST http://localhost:8080/api/articles \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Article '"$RANDOM"'",
    "paragraphs": ["First paragraph", "Second paragraph", "Third paragraph"],
    "categoryId": "0NB6E72VDBMMH"
  }'
