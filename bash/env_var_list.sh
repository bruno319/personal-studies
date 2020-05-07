#!/bin/bash

TODAY="$(date +"%Y-%m-%d")"
sudo mkdir -p /backup/conf/"$TODAY"/

printenv | sudo tee /backup/conf/"$TODAY"/env_data.txt