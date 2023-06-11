FROM azul/zulu-openjdk:19-latest

RUN apt update
RUN mkdir /app
ADD worker.jar /app

WORKDIR /app
CMD java -jar worker.jar

