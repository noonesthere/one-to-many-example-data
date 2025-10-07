#!/bin/bash

set -e
curl -X POST http://localhost:8080/api/articles/0000000000001 \
  -H "Content-Type: application/json" \
  -d "{\"articleId\":\"0000000000001\",\"grade\":$((RANDOM % 10 + 1))}"

curl -X POST http://localhost:8080/api/articles/0000000000002 \
  -H "Content-Type: application/json" \
  -d "{\"articleId\":\"0000000000002\",\"grade\":$((RANDOM % 10 + 1))}"

curl -X POST http://localhost:8080/api/articles/0000000000003 \
  -H "Content-Type: application/json" \
  -d "{\"articleId\":\"0000000000003\",\"grade\":$((RANDOM % 10 + 1))}"
