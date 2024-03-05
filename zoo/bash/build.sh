#!/bin/bash
cd "$1" && mvn clean package -Dmaven.test.skip
