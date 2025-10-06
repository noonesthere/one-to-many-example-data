#!/bin/bash

set -e

curl -X POST http://localhost:8080/api/categories/7J455XHCBZVN3 \
  -H "Content-Type: application/json" \
  -d '{"id": "7J455XHCBZVN3", "name": "TestsNew"}'
