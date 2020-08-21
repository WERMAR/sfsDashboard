@echo off

SET WORKDIR=%undefined%
SET %undefined%DOCKERBUILD="docker build -t"
SET %undefined%DOCKERFILESERVER="builderboard-server-img %CD%"
SET %undefined%DOCKERFILEWEB="builderboard-web-img %CD%"
echo "WORKDIR set on %WORKDIR%"
echo 
echo "switch to server directory"
cd %WORKDIR%\server\buildersBoard
echo %undefined%
echo "execute build command of docker"
command %DOCKERBUILD% %DOCKERFILESERVER%
echo 
echo "successfully created builderboard-server-img -> creating now web image"
cd %WORKDIR%\web\buildersBoard
echo %undefined%
echo "execute build command to build web image"
command %DOCKERBUILD% %DOCKERFILEWEB%
