#!/usr/bin/env bash

#Stop and remove all containers
./jdbcexample/src/main/resources/scripts/stop_n_remove_containers.sh ;

#Set exit on failure flag
set -e

#Copy all report templates to sql scripts folder
cd ./jdbcexample/src/main/resources/scripts/ ;
./copy_reports_to_volume.sh ;
cd - ;

#Copy all sql scripts
cd ./jdbcexample/src/main/resources/scripts/ ;
./copy_sql_files_to_volume.sh ;
cd - ;

#Providing extra rights for reports
#cd ./jdbcexample/volumes/mysql-dump/data/reports ;
#chmod a+x ./* ;
#cd - ;

#Build project
cd ./jdbcexample ;
./gradlew clean build docker ;
cd .. ;

#Run containers
docker-compose up