FROM azul/zulu-openjdk:19-latest

RUN apt update
RUN mkdir /app
ADD rest.jar /app

WORKDIR /app
CMD java -jar rest.jar

