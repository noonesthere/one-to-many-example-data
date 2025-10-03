plugins {
  alias(libs.plugins.spring.boot)
  alias(libs.plugins.spring.dependency.management)
}


base {
  archivesName = "plain-jdbc"
  group = "com.example.plain.jdbc"
}


configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}


dependencies {
  implementation(project(":domain"))
  implementation(project(":scenarios"))
  implementation(project(":rest"))
  implementation(project(":plain-jdbc:persistence"))

  implementation(libs.spring.boot.starter)

  annotationProcessor(libs.spring.boot.configuration.processor)

  testImplementation(libs.spring.boot.starter.test)
  testRuntimeOnly(libs.junit.platform.launcher)
}

