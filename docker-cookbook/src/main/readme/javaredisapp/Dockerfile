FROM azul/zulu-openjdk:19-latest

RUN apt update
RUN apt install curl -y
RUN apt install iputils-ping -y
RUN apt install tcptraceroute -y
RUN apt install net-tools -y

RUN mkdir /app
ADD javaredisapp.jar /app

WORKDIR /app
CMD java -jar javaredisapp.jar
