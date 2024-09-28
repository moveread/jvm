plugins {
  `java-library`
  `maven-publish`
}

group = "com.moveread"
version = "0.0.0"

repositories {
  mavenCentral()
}

dependencies {
  implementation("com.google.code.gson:gson:2.11.0")
  implementation("org.jetbrains:annotations:24.0.0")
}

publishing {
  publications {
    create<MavenPublication>("mavenJava") {
      from(components["java"])

      groupId = project.group.toString()
      artifactId = "tnmt"
      version = project.version.toString()

      pom {
        name.set("Moveread for Tournaments API Client")
      }
    }
  }

  repositories {
    maven("file://${project.buildDir}/repo")
  }
}