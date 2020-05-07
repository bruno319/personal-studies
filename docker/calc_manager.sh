#!/bin/bash

function buildMicroservice() {
    docker build -t "tema7/calc-go" .
    docker image prune --filter label=stage=builder
}

function startMicroservice() {
    CONTAINER_ID=$(docker run -d -p 8282:8282 tema7/calc-go | tee /dev/tty)
}

function stopMicroservice() {
    docker stop "$CONTAINER_ID"
}

function statusMicroservice() {
    STATUS=$(docker inspect -f '{{.State.Running}}' "$CONTAINER_ID")
    if [ "$STATUS" = true ] ; then
      echo "RUNNING"
    else
      echo "NOT RUNNING"
    fi
}