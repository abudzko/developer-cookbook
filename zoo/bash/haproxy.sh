#!/bin/bash
docker_dev_path="../docker-dev/";
if [ "$1" = "down" ]; then
  cd $docker_dev_path && docker-compose -f docker-compose-haproxy.yml down
elif [ "$1" = "up" ]; then
  sh haproxy.sh down
  cd $docker_dev_path && docker-compose -f docker-compose-haproxy.yml up
else
  echo "'up' or 'down' parameter is expected"
fi
