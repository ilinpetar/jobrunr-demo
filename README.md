# JobRunr concurrency demo

This project shows how to put a limit on the number of concurrent jobs JobRunr's background job server is executing.

Simply setting the worker count in [application.properties](src/main/resources/application.properties) limits the number of concurrent jobs to 2:

```properties
org.jobrunr.background-job-server.worker-count=2
```

## Running the project

Run the project from the command line:

```shell
./gradlew bootRun
```

or from the IDE run configurations. While running, observe the service logs or monitor the JobRunr's [dashboard](http://localhost:8000)