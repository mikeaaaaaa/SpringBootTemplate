#!/bin/bash

currentDir=$(pwd)

imageName=springboot-init
innerPort=8101
outerPort=8089
# 使用哪个配置文件
activeProfile=test
# 容器名称
containerName=$imageName

if [ ! -f "./config/application.properties"  ] || [ ! -f "./config/application-$activeProfile.properties"  ]; then
    echo "there is no application.properties or application-$activeProfile.properties file in the current directory: $currentDir"
    exit 1
fi

docker rm -f $containerName
# :ro表示只读挂载
docker run -d --name $containerName -p $outerPort:$innerPort -v ./logs:/app/logs -v ./config/application.yml:/app/config/application.yml:ro -v ./config/application-$activeProfile.yml:/app/config/application-$activeProfile.yml:ro -e SPRING_PROFILES_ACTIVE=$activeProfile $imageName:latest