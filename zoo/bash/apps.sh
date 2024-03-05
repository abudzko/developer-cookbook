#!/bin/bash
docker_dev_path="../docker-dev/";
if [ "$1" = "down" ]; then
  cd $docker_dev_path && docker-compose -f docker-compose-apps.yml down
elif [ "$1" = "up" ]; then
  rest_app_path="../rest"
  worker_app_path="../worker"
  sh build.sh $rest_app_path
  sh build.sh $worker_app_path
  docker build $rest_app_path/target/ -f $rest_app_path/Dockerfile --tag zoo-rest
  docker build $worker_app_path/target/ -f $worker_app_path/Dockerfile --tag zoo-worker
  sh apps.sh down
  cd $docker_dev_path && docker-compose -f docker-compose-apps.yml up
else
  echo "'up' or 'down' parameter is expected"
fi
