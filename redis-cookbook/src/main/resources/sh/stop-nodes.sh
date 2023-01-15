#!/bin/bash

cd /home/aliaksei/install/redis/bin/
./redis-cli -h localhost -p 7001 shutdown &
./redis-cli -h localhost -p 7002 shutdown &
./redis-cli -h localhost -p 7003 shutdown &
./redis-cli -h localhost -p 7004 shutdown &
./redis-cli -h localhost -p 7005 shutdown &
./redis-cli -h localhost -p 7006 shutdown &
