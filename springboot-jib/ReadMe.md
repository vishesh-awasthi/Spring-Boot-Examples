## What is Jib?

Jib builds optimized Docker and [OCI](https://github.com/opencontainers/image-spec) images for your Java applications without a Docker daemon - and without deep mastery of Docker best-practices. It is available as plugins for [Maven](jib-maven-plugin) and [Gradle](jib-gradle-plugin) and as a Java library.

[Maven](https://maven.apache.org/): See documentation for [jib-maven-plugin](jib-maven-plugin).\
[Gradle](https://gradle.org/): See documentation for [jib-gradle-plugin](jib-gradle-plugin).

For more information, check out the [official blog post](https://cloudplatform.googleblog.com/2018/07/introducing-jib-build-java-docker-images-better.html) or watch [this talk](https://www.youtube.com/watch?v=H6gR_Cv4yWI) ([slides](https://speakerdeck.com/coollog/build-containers-faster-with-jib-a-google-image-build-tool-for-java-applications)).
***
## Goals
* **Fast** - Deploy your changes fast. Jib separates your application into multiple layers, splitting dependencies from classes. Now you don’t have to wait for Docker to rebuild your entire Java application - just deploy the layers that changed.
* **Reproducible** - Rebuilding your container image with the same contents always generates the same image. Never trigger an unnecessary update again.
* **Daemonless** - Reduce your CLI dependencies. Build your Docker image from within Maven or Gradle and push to any registry of your choice. *No more writing Dockerfiles and calling docker build/push.*

***
In this example we are using jib gradle plugin so, we just have to do couple of things to create and publish the docker image to docker hub
* Add the following to **build.gradle** and then run `gradle jib`
```
plugins {
    id 'com.google.cloud.tools.jib' version '2.7.0'
}

jib.to.image= 'Image path'
```

[Reference](https://github.com/GoogleContainerTools/jib/tree/master/jib-gradle-plugin) 