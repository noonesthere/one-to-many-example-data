#!/bin/bash

set -e

curl -X POST http://localhost:8080/api/categories/0NB6E72VDBMMH \
  -H "Content-Type: application/json" \
  -d '{"id": "0NB6E72VDBMMH", "name": "Renamed Category '"$RANDOM"'"}'
