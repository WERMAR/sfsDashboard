#!/bin/bash

# setup local variables
WORKDIR=`pwd`
DOCKERBUILD="docker build -t"
DOCKERFILESERVER="builderboard-server-img ."
DOCKERFILEWEB="builderboard-web-img ."
echo "WORKDIR set on $WORKDIR"

# execute build
echo ""
echo "switch to server directory"
cd $WORKDIR/server/buildersBoard
echo `pwd`
echo "execute build command of docker"
command $DOCKERBUILD $DOCKERFILESERVER
echo ""
echo "successfully created builderboard-server-img -> creating now web image"
cd $WORKDIR/web/builderBoard
echo `pwd`
echo "execute build command to build web image"
command $DOCKERBUILD $DOCKERFILEWEB
