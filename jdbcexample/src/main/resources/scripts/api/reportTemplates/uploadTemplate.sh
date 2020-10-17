#!/usr/bin/env bash

curl  -XPOST --header 'Accept:application/json' --header 'Content-Type:application/json' \
--data '{"description": "template for testing", "templateName": "test_template.txt", "template": [48, 49, 50, 51, 52, 53, 54, 55, 56, 57] }' \
http://localhost:8080/api/v1/report