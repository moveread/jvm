# Moveread for Tournaments - Java/Kotlin Clients

Java and Kotlin clients for the Moveread for Tournaments API

## Installation (Gradle)

```kotlin
// build.gradle.kts

repositories {
  // ...
  maven("https://moveread-maven.s3.amazonaws.com/")
}

dependencies {
  // ...
  implementation("com.moveread:tnmt:0.0.0")
}
```

## Usage

```java
import com.moveread.tnmt.Client;

var client = new Client();
var tnmts = client.getTnmts().join();
// ...
```