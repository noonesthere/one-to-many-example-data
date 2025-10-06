plugins {
  alias(libs.plugins.spring.boot)
  alias(libs.plugins.spring.dependency.management)
}


base {
  archivesName = "data-jpa"
  group = "com.example.data.jpa"
}


configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}


dependencies {
  implementation(project(":scenarios"))
  implementation(project(":rest"))
  implementation(project(":data-jpa:persistence"))

  implementation(libs.spring.boot.starter)

  implementation(libs.spring.boot.starter.data.jpa)
  runtimeOnly(libs.h2)


  annotationProcessor(libs.spring.boot.configuration.processor)
  testImplementation(libs.spring.boot.starter.test)

  testRuntimeOnly(libs.junit.platform.launcher)
}
