#!/bin/bash

# JAVA_HOME
#export JAVA_HOME="/usr/lib/jvm/java-11-amazon-corretto.x86_64/"

# cd
cd /home/ec2-user/spring-boot2-train

# chmod
chmod 755 mvnw

# spring-boot:run
nohup ./mvnw spring-boot:run &
