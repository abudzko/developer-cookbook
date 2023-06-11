#!/bin/bash
sh down-kafka.sh
cd ../docker-dev/ && docker-compose -f docker-compose-kafka.yml up
