#!/bin/bash

echo "Pass the path to folder to be zipped"
read path

TODAY="$(date +"%Y-%m-%d")"
sudo mkdir -p /backup/data/"$TODAY"/

echo "Insert the name of the zip file"
read zipName

zip -r "$zipName" "$path"
sudo mv "$zipName".zip /backup/data/"$TODAY"/