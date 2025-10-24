#!/bin/bash

set -e

# Check if argument provided
if [ -z "$1" ]; then
  echo "Usage: $0 <categoryId>"
  exit 1
fi

categoryId="$1"

curl -X POST "http://localhost:8080/api/categories/${categoryId}" \
  -H "Content-Type: application/json" \
  -d "{\"id\": \"${categoryId}\", \"name\": \"Renamed Category ${RANDOM}\"}"
