#!/usr/bin/env bash

./jdbcexample/src/main/resources/scripts/stop_n_remove_containers.sh ;

cd ./jdbcexample/src/main/resources/scripts/ ;
./copy_sql_files_to_volume.sh ;
cd - ;

cd ./jdbcexample ;
./gradlew clean build docker ;
cd .. ;

docker-compose up