#!/bin/bash

set -e

# Check if argument provided
if [ -z "$1" ]; then
  echo "Usage: $0 <articleId>"
  exit 1
fi

articleId="$1"

curl -X POST "http://localhost:8080/api/articles/${articleId}/title" \
  -H "Content-Type: application/json" \
  -d "{
    \"articleId\": \"${articleId}\",
    \"title\": \"Renamed Title ${RANDOM}\"
  }"
