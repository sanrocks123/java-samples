#!/bin/bash

if [[ -z $DOCKER_HOST ]] ; then
 echo -e "\nError : Please set DOCKER_HOST env variable. Example DOCKER_HOST=tcp://<DOCKER_HOST_IP>:<DAEMON_PORT>\n"
  exit 1
  fi

  if [[ -z $1 || -z $2 ]] ; then
   echo -e "\nUsage: pushImage imageName tag\n"
    exit 1
    fi

    dcsDockerRegistry="registry.paas.symcpe.com:443/dcs-cloud"
    server=$DOCKER_HOST
    image=$1
    tag=$2

    serverIp=$(echo $server| cut -d'/' -f 3)

    echo -e "\nTagging image..."
    taggedImage=$dcsDockerRegistry/$image:$tag
    docker tag $image:latest $taggedImage
    echo -e "Image tagged - $taggedImage"

    dockerPushApiURL=http://$serverIp/images/$taggedImage/push

    echo -e "\nPushing image - $dockerPushApiURL\nPlease wait...\n"
    curl -i -X POST -H "X-Registry-Auth:787f5fe5195c40ef924ac8d67948e15a" $dockerPushApiURL
    echo -e "\nSuccess!"

    #echo -e "\nListing Repository - $dcsDockerRegistry\n"
    #docker search $dcsDockerRegistry

