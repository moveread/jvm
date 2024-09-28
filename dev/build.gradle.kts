plugins {
  application
}

repositories {
  mavenCentral()
  maven("https://moveread-maven.s3.amazonaws.com/")
}

dependencies {
  // implementation(project(":client"))
  implementation("com.moveread:tnmt:0.0.0")
  implementation("com.google.code.gson:gson:2.11.0")
}

application {
  mainClass = "Main"
}