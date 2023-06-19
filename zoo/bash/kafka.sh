#!/bin/bash
docker_dev_path="../docker-dev/"
kafka_volumes_dir=~/Downloads/tmp/volumes/kafka
if [ "$1" = "down" ]
then
  cd $docker_dev_path && docker-compose -f docker-compose-kafka.yml down
  if [ -d $kafka_volumes_dir ]
  then
    rm -r ${kafka_volumes_dir:?}
    echo "Removed: "$kafka_volumes_dir
  fi
elif [ "$1" = "up" ]; then
  sh kafka.sh down
  if [ ! -d $kafka_volumes_dir ]
  then
      mkdir $kafka_volumes_dir
  fi
  cd $docker_dev_path && docker-compose -f docker-compose-kafka.yml up
else
  echo "'up' or 'down' parameter is expected"
fi
