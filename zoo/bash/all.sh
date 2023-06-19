#!/bin/bash
if [ "$1" = "down" ]; then
  sh apps.sh down
  sh kafka.sh down
  sh haproxy.sh down
  sh postgres.sh down
elif [ "$1" = "up" ]; then
  all.sh down
  sh postgres.sh up &
  sh haproxy.sh up &
  sh kafka.sh up &
  sh apps.sh up &
else
  echo "'up' or 'down' parameter is expected"
fi
