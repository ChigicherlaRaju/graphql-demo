# <center> graphql-demo </center>

Spring boot GraphQL Demo Application

#### Below are the details used for the application:

- Application Name: `graphql-demo`
- Port No: `8080`
- Docker Image Name: `graphql-demo`
- Docker Image Tag: `latest`
- Docker Container Name: `graphql-demo-container`

### Create/Build the Docker Image:

- Open a terminal and navigate to the project directory.
- Run the following command to build the Docker image:

```shell
docker build -t graphql-demo:latest .
```

#### Run the following command to verify the Docker image:

```shell
docker images
```

### Run the Docker Container:

Run the following command to create and start a container from the built image:

```shell
docker run -d --name graphql-demo-container -p 8080:8080 graphql-demo:latest
```

#### Run the following command to verify the Docker container:

```shell
docker ps
```

#### Run the following command to get into the container:

```shell
docker exec -it graphql-demo-container /bin/sh
```

or

```shell
docker exec -it graphql-demo-container /bin/bash
```

#### Run the following command to check the logs

```shell
docker logs -f graphql-demo-container
```
