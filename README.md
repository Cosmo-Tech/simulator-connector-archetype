# Simulator Connector Archetype

# How to generate a project with this archetype

```
mvn archetype:generate \
 -B \
 -DarchetypeArtifactId=simulator-connector-archetype \
 -DarchetypeGroupId=com.cosmotech \
 -DgroupId=<your_groupId> \
 -DartifactId=<your_artifactId> \
 -DarchetypeVersion=1.0-SNAPSHOT
```

The command above will generate a project folder named "<your_artifactId>".

In this folder, you will find a connector's example within the HelloWorldConnector.java class.

You can open this project into your IDE and start to create your own connector!

# Build your image

Change the default container registry

```
  <to>
    <image>your_container_registry/<your_artifactId></image>
  </to>
```
See [Jib project Configuration]("https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin#configuration") to set correctly your container registry (GCR, ECR, ACR, Docker Hub Registry)

Build your container image with:

```shell
mvn compile jib:build
```

Subsequent builds are much faster than the initial build.

#### Build to Docker daemon

Jib can also build your image directly to a Docker daemon. This uses the `docker` command line tool and requires that you have `docker` available on your `PATH`.

```shell
mvn compile jib:dockerBuild
```

For more information, see [Jib project Build]("https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin#build-your-image")