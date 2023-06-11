#!/bin/bash
sh down-postgres.sh
cd ../docker-dev/ && docker-compose -f docker-compose-postgres.yml up
