plugins {
  alias(libs.plugins.spring.boot)
  alias(libs.plugins.spring.dependency.management)
}

base {
  archivesName = "data-jdbc"
  group = "com.example.data.jdbc"
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
  implementation(project(":data-jdbc:persistence"))
  implementation(project(":log"))

  implementation("org.springframework.modulith:spring-modulith-starter-core") // TODO move to toml
  implementation("org.springframework.modulith:spring-modulith-events-jackson")
  implementation("org.springframework.modulith:spring-modulith-events-jdbc")

  implementation(libs.spring.boot.starter)
  implementation(libs.spring.boot.starter.data.jdbc)

  runtimeOnly(libs.h2)


  annotationProcessor(libs.spring.boot.configuration.processor)

  testImplementation(libs.spring.boot.starter.test)
  testImplementation(libs.junit.jupiter)

  testRuntimeOnly(libs.junit.platform.launcher)
}

dependencyManagement {
  imports {
    mavenBom("org.springframework.modulith:spring-modulith-bom:1.4.1") //TODO: use toml
  }
}


