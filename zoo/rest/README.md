# event
### GET : /generateEvent, /event, /events
### Generated event is sent into kafka topic
### Generated event is stored in master instance of postgres db
### Event is read from slave instance of postgres db
### Used two datasources to support two connections to master and slave db
### see [docker-compose-postgres.yml](..%2Fdocker-dev%2Fdocker-compose-postgres.yml)

# request
### Request-Response approach with Kafka
### GET /sendRequest
### Create [AsyncRequest.java](src%2Fmain%2Fjava%2Fbudzko%2Fzoo%2Frequest%2Fkafka%2Fprocuder%2FAsyncRequest.java) and send it into Kafka topic
### [worker](..%2Fworker) will process request and will send [AsyncResponse.java](src%2Fmain%2Fjava%2Fbudzko%2Fzoo%2Frequest%2Fkafka%2Fconsumer%2Fresponse%2FAsyncResponse.java)
### Response will be sent to partition which assigned to rest app instance which has sent the request
### Each AsyncRequest contains partition(id) from which rest app reads response

# Kafka Client
### Management of kafka cluster: get topics list, create/delete topic
