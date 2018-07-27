#!/bin/bash

# 启动服务器方式
uname
hostname
SERVER_PATH='./target/socket-servers-1.0-SNAPSHOT-jar-with-dependencies.jar'
JAVA_VERSION=`java -version 2>&1 |awk 'NR==1{ gsub(/"/,""); print $3 }'`
echo 'java:'$JAVA_VERSION
echo 'starting...'
echo 'server path:'$SERVER_PATH
java -jar $SERVER_PATH
echo "socket server stop."
