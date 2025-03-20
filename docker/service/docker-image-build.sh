serviceProjectName=springboot-init
serviceProjectVersion=0.0.1-SNAPSHOT

currentDir=$(pwd)
imageName=$serviceProjectName
tag=$serviceProjectVersion
imageFileName=$imageName-$tag.tar

targetPlatform=linux/amd64
dockerPath="$currentDir"/docker/service

docker pull openjdk:8-jdk-alpine
docker build -f "$dockerPath"/Dockerfile --build-arg SERVICE_PROJECT_NAME=$serviceProjectName --build-arg SERVICE_PROJECT_VERSION=$serviceProjectVersion -t $imageName:$tag -t $imageName:latest --platform=$targetPlatform .
docker save -o "$dockerPath"/build/$imageFileName $imageName:latest