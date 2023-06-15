#!/bin/bash
cd ../docker-dev/ && docker-compose -f docker-compose-kafka.yml down
rm -r ~/Downloads/tmp/volumes/kafka/data/
