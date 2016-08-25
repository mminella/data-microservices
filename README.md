# Time | Log

* `$ cd /Users/mminella/Documents/IntelliJWorkspace/spring-cloud-stream-app-starters/apps`
* `$ java -jar time-source-rabbit/target/time-source-rabbit-1.0.3.BUILD-SNAPSHOT.jar --spring.cloud.stream.bindings.output.destination=ticktock`
* `$ java -jar log-sink-rabbit/target/log-sink-rabbit-1.0.3.BUILD-SNAPSHOT.jar --spring.cloud.stream.bindings.input.destination=ticktock --server.port=9090`

# 5:00 Somewhere

* `$ cd /Users/mminella/Documents/IntelliJWorkspace/spring-cloud-stream-app-starters/apps`
* `$ java -jar time-source-rabbit/target/time-source-rabbit-1.0.3.BUILD-SNAPSHOT.jar --spring.cloud.stream.bindings.output.destination=fiveoclockinput`
* `$ cd /Users/mminella/Documents/IntelliJWorkspace/data-microservices/five-oclock-transformer`
* `$ java -jar target/five-oclock-transformer-0.0.1-SNAPSHOT.jar --spring.cloud.stream.bindings.input.destination=fiveoclockinput --spring.cloud.stream.bindings.output.destination=fiveoclockoutput --server.port=8585`
* `$ cd /Users/mminella/Documents/IntelliJWorkspace/spring-cloud-stream-app-starters/apps`
* `$ java -jar log-sink-rabbit/target/log-sink-rabbit-1.0.3.BUILD-SNAPSHOT.jar --spring.cloud.stream.bindings.input.destination=fiveoclockoutput --server.port=9090`

# Task demo

* Create database: `mysql:> create database spring_cloud_task;`
* `$ cd /Users/mminella/Documents/IntelliJWorkspace/data-microservices/task-demo`
* `$ java -jar target/task-demo-0.0.1-SNAPSHOT.jar`

# Twitter Streaming Demo

* `$ cd /Users/mminella/Documents/IntelliJWorkspace/data-microservices/`
* `$ java -jar dataflow-server/target/data-flow-0.0.1-SNAPSHOT.jar`
* `$ java -jar dataflow-shell/target/dataflow-shell-0.0.1-SNAPSHOT.jar`
* `dataflow:> app list`
* `dataflow:> app register --type source --name twitter --uri maven://org.springframework.cloud.stream.app:twitterstream-source-rabbit:1.0.3.BUILD-SNAPSHOT`
* `dataflow:> app register --type sink --name file --uri maven://org.springframework.cloud.stream.app:file-sink-rabbit:1.0.3.BUILD-SNAPSHOT`
* `dataflow:> app register --type sink --name field-value-counter --uri maven://org.springframework.cloud.stream.app:field-value-counter-sink-rabbit:1.0.0.BUILD-SNAPSHOT`
* `dataflow:> stream create --definition "twitter | file --directory='/Users/mminella'" --name ingest --deploy`
* `dataflow:> stream create --name tweetsCount --definition ":ingest.twitter > field-value-counter --field-name=entities.hashtags.text --name=tweetsCountTags" --deploy`




# Debugging Twitter

* `dataflow:> app register --type sink --name log --uri maven://org.springframework.cloud.stream.app:log-sink-rabbit:1.0.3.BUILD-SNAPSHOT`
* `dataflow:> stream create --definition "twitter | log" --name logTwitter --deploy`