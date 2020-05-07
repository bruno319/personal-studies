#!/bin/bash

APPLICATION_NAME="calc-microservice"
CALC_PID=$(pgrep -f $APPLICATION_NAME)

if [ -n "$CALC_PID" ]; then
  echo "$APPLICATION_NAME is running. PID: $CALC_PID"
else
  echo "$APPLICATION_NAME is not running!"
fi
