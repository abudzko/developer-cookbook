#!/bin/bash
docker build ../rest/target/ -f ../rest/Dockerfile --tag zoo-rest
docker build ../worker/target/ -f ../worker/Dockerfile --tag zoo-worker
sh down-app.sh
cd ../docker-dev/ && docker-compose -f docker-compose-apps.yml up
