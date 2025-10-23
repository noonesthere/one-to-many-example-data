#!/bin/bash

set -e

curl -X POST http://localhost:8080/api/categories/0NB663JYQH8ZM \
  -H "Content-Type: application/json" \
  -d '{"id": "0NB663JYQH8ZM", "name": "Second Category Name"}'
