#!/bin/sh

echo "The App is starting ..."

exec java -jar -Dspring.profiles.active=${SPRING_ACTIVE_PROFILES} "CRUD_Test.jar"