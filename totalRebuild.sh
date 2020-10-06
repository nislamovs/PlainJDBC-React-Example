#!/usr/bin/env bash

#Exit on failure
set -e

#Stop and remove all containers
./jdbcexample/src/main/resources/scripts/stop_n_remove_containers.sh ;


#Copy all sql scripts
cd ./jdbcexample/src/main/resources/scripts/ ;
./copy_sql_files_to_volume.sh ;
cd - ;

#Build project
cd ./jdbcexample ;
./gradlew clean build docker ;
cd .. ;

#Run containers
docker-compose up