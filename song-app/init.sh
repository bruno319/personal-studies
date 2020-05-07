#!/usr/bin/env bash

mkdir -p "$HOME/logs"
mkdir -p "$HOME/data/db-song-service"
mkdir -p "$HOME/data/db-playlist-service"

mongod --logpath "$HOME"/logs/log1.log --dbpath "$HOME"/data/db-playlist-service --port 27200 --fork
mongod --logpath "$HOME"/logs/log2.log --dbpath "$HOME"/data/db-song-service --port 27300 --fork

./gradlew :server:bootRun &
./gradlew :song-service:bootRun -Pargs=--port=8090 &
./gradlew :song-service:bootRun -Pargs=--port=8092 &
./gradlew :playlist-service:bootRun &
./gradlew :app-service:bootRun &
