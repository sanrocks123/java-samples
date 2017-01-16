FROM isuper/java-oracle:server_jre_8

MAINTAINER sanjeev

ENV SERVICE spring-boot
ENV DCS_DIR /usr/local/symantec/dcs-cloud

ENV CONFIG=config
ENV LIB=lib
ENV PERF_OPTS="-XX:+HeapDumpOnOutOfMemoryError -XX:+UseG1GC"
ENV MEM_OPTS="-Xms256m -Xmx2048m"
ENV APP_HOME="-DAPP_HOME=$PWD"
ENV JAVA_SECURITY="-Djava.security.properties=$PWD/java.security"

RUN mkdir -p $DCS_DIR/$SERVICE

WORKDIR $DCS_DIR/$SERVICE

RUN mkdir -p $LIB
RUN mkdir -p $CONFIG

COPY ./lib ./$LIB
COPY ./*.jar ./$LIB

EXPOSE 8080

ENTRYPOINT java -server $JAVA_SECURITY $APP_HOME $PERF_OPTS $MEM_OPTS -cp .:"$LIB/*" com.sanjeev.spring.boot.main.Main $*
