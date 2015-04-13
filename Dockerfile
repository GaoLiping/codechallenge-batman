FROM ubuntu:trusty
MAINTAINER Soren Vind <svi@tradeshift.com>

RUN apt-get update && apt-get install -y wget openjdk-7-jre-headless

ENV JETTY_INSTALL_DIR /opt/jetty
ENV JETTY_VERSION 9.2.10
ENV RELEASE_DATE v20150310

RUN wget http://download.eclipse.org/jetty/stable-9/dist/jetty-distribution-${JETTY_VERSION}.${RELEASE_DATE}.tar.gz && \
    tar -xzvf jetty-distribution-${JETTY_VERSION}.${RELEASE_DATE}.tar.gz && \
    rm -rf jetty-distribution-${JETTY_VERSION}.${RELEASE_DATE}.tar.gz && \
    mv jetty-distribution-${JETTY_VERSION}.${RELEASE_DATE} ${JETTY_INSTALL_DIR}

# Configure Jetty user and clean up install
RUN useradd jetty && \
    chown -R jetty:jetty ${JETTY_INSTALL_DIR} && \
    rm -rf ${JETTY_INSTALL_DIR}/demo-base

WORKDIR ${JETTY_INSTALL_DIR}

ADD rws/target/rws-0.1.war webapps/root.war

EXPOSE 8080

# run docker
CMD ["java", "-jar", "start.jar", "jetty.home=/opt/jetty"]

