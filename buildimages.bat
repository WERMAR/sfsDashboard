@echo off
echo 
echo "switch to server directory"
cd .\server\buildersBoard
echo %undefined%
echo "execute build command of docker"
docker build -t builderboard-server-img .
echo 
echo "successfully created builderboard-server-img -> creating now web image"
cd .\web\buildersBoard
echo "execute build command to build web image"
docker build -t builderboard-web-img .
