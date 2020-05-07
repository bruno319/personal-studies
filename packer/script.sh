#!/bin/bash

AUTHOR="brunovieira"
APPLICATION_NAME="calc-microservice"
VERSION="1.0"
PORT="8282"

function buildCalculator() {
  go build -o $APPLICATION_NAME main.go
}

function buildDockerImage() {
  sudo packer build \
    -var "application_name=$APPLICATION_NAME" \
    -var "author=$AUTHOR" \
    -var "version=$VERSION" \
    packer-calc.json
}

function runImageContainer() {
  echo "Starting a container"
  IMAGE_NAME="$AUTHOR/$APPLICATION_NAME:$VERSION"
  sudo docker run -p $PORT:8282 $IMAGE_NAME
}

buildCalculator
buildDockerImage
runImageContainer
