#!/bin/bash
docker_dev_path="../docker-dev"
pg_volumes_dir=~/Downloads/tmp/volumes/pg
if [ "$1" = "down" ]
then
  cd $docker_dev_path && docker-compose -f docker-compose-postgres.yml down
  if [ -d $pg_volumes_dir ]
  then
    rm -rf ${pg_volumes_dir:?}
    echo "Removed: "$pg_volumes_dir
  fi
elif [ "$1" = "up" ]; then
  sh postgres.sh down
  if [ ! -d $pg_volumes_dir ]
  then
      mkdir $pg_volumes_dir
      mkdir $pg_volumes_dir/master
      mkdir $pg_volumes_dir/master/conf
      cp $docker_dev_path/postgres/postgresql-master.conf $pg_volumes_dir/master/conf/postgresql.conf
      mkdir $pg_volumes_dir/slave1
      mkdir $pg_volumes_dir/slave1/conf
      cp $docker_dev_path/postgres/postgresql-slave.conf $pg_volumes_dir/slave1/conf/postgresql.conf
      mkdir $pg_volumes_dir/slave2
      mkdir $pg_volumes_dir/slave2/conf
      cp $docker_dev_path/postgres/postgresql-slave.conf $pg_volumes_dir/slave2/conf/postgresql.conf
  fi
  cd $docker_dev_path && docker-compose -f docker-compose-postgres.yml up
else
  echo "'up' or 'down' parameter is expected"
fi
