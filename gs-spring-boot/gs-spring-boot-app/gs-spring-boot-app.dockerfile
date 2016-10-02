#
# Java 1.8 & Maven Dockerfile
#
#

# pull base image.
FROM java:8

# update packages and install maven
RUN  \
  export DEBIAN_FRONTEND=noninteractive && \
  sed -i 's/# \(.*multiverse$\)/\1/g' /etc/apt/sources.list && \
  apt-get update && \
  apt-get -y upgrade && \
  apt-get install -y vim wget curl maven

# ports
EXPOSE 8080

# attach volumes
ADD . /vol/development

# create working directory
WORKDIR /vol/development

# maven exec
CMD ["mvn", "spring-boot:run"]
