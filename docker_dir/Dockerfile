FROM      ubuntu:trusty
#FROM test/jetty:v5
MAINTAINER liping <lga@tradeshift.com>

## make sure the package repository is up to date
RUN echo "deb http://archive.ubuntu.com/ubuntu precise main universe" > /etc/apt/sources.list
RUN echo "deb http://archive.ubuntu.com/ubuntu precise-updates main universe" >> /etc/apt/sources.list
RUN apt-get update


##dependencies
RUN apt-get install -y openjdk-7-jre-headless tar

##Jetty
RUN adduser --system jetty
RUN mkdir /opt/jetty

ADD http://download.eclipse.org/jetty/stable-9/dist/jetty-distribution-9.2.10.v20150310.tar.gz /opt/jetty.tar.gz

RUN cat /opt/jetty.tar.gz | tar -C /opt/jetty --strip-components=1 -xz
RUN chown -R jetty /opt/jetty
ADD helloworld.war /opt/jetty/webapps/helloworld.war
##ADD start_jetty.sh /opt/jetty/start_jetty.sh
##CMD /opt/jetty/start_jetty.sh


##run jetty
USER jetty
EXPOSE 8080
#CMD ["java", "-Djetty.home=/opt/jetty", "-jar", "/opt/jetty/start.jar"]
CMD [ "/opt/jetty/bin/jetty.sh", "-d", "run"]