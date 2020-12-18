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