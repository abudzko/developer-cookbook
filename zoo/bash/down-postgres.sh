#!/bin/bash
rm -rf ~/Downloads/tmp/volumes/pg/master/data/*
rm -rf ~/Downloads/tmp/volumes/pg/slave1/data/*
rm -rf ~/Downloads/tmp/volumes/pg/slave2/data/*
cd ../docker-dev/ && docker-compose -f docker-compose-postgres.yml down
