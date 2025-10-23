#!/bin/bash

set -e

curl -X POST http://localhost:8080/api/categories/0NB5JAW25YMZQ \
  -H "Content-Type: application/json" \
  -d '{"id": "0NB5JAW25YMZQ", "name": "Second Category Name"}'
