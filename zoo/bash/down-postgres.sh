#!/bin/bash
rm -rf ~/Downloads/tmp/volumes/pg/master/data/*
rm -rf ~/Downloads/tmp/volumes/pg/slave/data/*
cd ../docker-dev/ && docker-compose -f docker-compose-postgres.yml down
