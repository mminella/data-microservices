* Start rabbitmq-server
* Start redis-server
* Delete contents of ~/outputs

# Time | Log

* `cd /Users/mminella/Documents/IntelliJWorkspace/time/apps/time-source-rabbit/target/`
* `java -jar time-source-rabbit-2.0.0.BUILD-SNAPSHOT.jar --spring.cloud.stream.bindings.output.destination=ticktock`
* `cd /Users/mminella/Documents/IntelliJWorkspace/log/apps/log-sink-rabbit/target/`
* `java -jar log-sink-rabbit-2.0.0.BUILD-SNAPSHOT.jar --server.port=9191 --spring.cloud.stream.bindings.input.destination=ticktock`

# Scale source
* `$ cd /Users/mminella/Documents/IntelliJWorkspace/spring-cloud-stream-app-starters/apps`
* `$ java -jar time-source-rabbit/target/time-source-rabbit-1.0.3.BUILD-SNAPSHOT.jar --spring.cloud.stream.bindings.output.destination=ticktock --server.port=9191`

# 5:00 Somewhere

* `cd /Users/mminella/Documents/IntelliJWorkspace/time/apps/time-source-rabbit/target/`
* `java -jar time-source-rabbit-2.0.0.BUILD-SNAPSHOT.jar --spring.cloud.stream.bindings.output.destination=fiveoclockinput`
* `cd /Users/mminella/Documents/IntelliJWorkspace/data-microservices/five-oclock-transformer`
* `java -jar target/five-oclock-transformer-0.0.1-SNAPSHOT.jar --spring.cloud.stream.bindings.input.destination=fiveoclockinput --spring.cloud.stream.bindings.output.destination=fiveoclockoutput --server.port=8585`
* `cd /Users/mminella/Documents/IntelliJWorkspace/log/apps/log-sink-rabbit/target/`
* `java -jar log-sink-rabbit-2.0.0.BUILD-SNAPSHOT.jar --server.port=9191 --spring.cloud.stream.bindings.input.destination=fiveoclockoutput`

# Task demo

* Create database: `mysql:> create database scdf;`
* `$ cd /Users/mminella/Documents/IntelliJWorkspace/data-microservices/task-demo`
* `$ java -jar target/task-demo-0.0.1-SNAPSHOT.jar`

# Twitter Streaming Demo

* `cd /Users/mminella/Documents/IntelliJWorkspace/data-microservices/bin`
* `./dataflow.sh`
* `cd /Users/mminella/Documents/IntelliJWorkspace/data-microservices/bin`
* `java -jar spring-cloud-dataflow-shell-1.4.0.RELEASE.jar`
* `dataflow:> app list`
* `dataflow:> app import --uri http://bit.ly/Celsius-SR1-stream-applications-rabbit-maven`
* `dataflow:> app list`
* `dataflow:> stream create --definition "twitterstream --twitter.credentials.consumerKey=<CONSUMER_KEY> --twitter.credentials.consumerSecret=<CONSUMER_SECRET> --twitter.credentials.accessToken=<ACCESS_TOKEN> --twitter.credentials.accessTokenSecret=ACCESS_TOKEN_SECRET> | file --directory='/Users/mminella/output'" --name ingest --deploy`
* `dataflow:> stream create --name tweetsCount --definition ":ingest.twitterstream > field-value-counter --field-name=entities.hashtags.text --name=tweetsCountTags" --deploy`

# Debugging Twitter

* `dataflow:> app register --type sink --name log --uri maven://org.springframework.cloud.stream.app:log-sink-rabbit:1.0.3.BUILD-SNAPSHOT`
* `dataflow:> stream create --definition "twitter | log" --name logTwitter --deploy`

# Task Demo on SCDF
* `app register --type task --name boring_task --uri maven://io.spring.cloud.task:task-demo:0.0.1-SNAPSHOT`
* `task create --name myBatchJob --definition 'boring_task'`