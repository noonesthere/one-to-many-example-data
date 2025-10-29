plugins {
  alias(libs.plugins.spring.boot)
  alias(libs.plugins.spring.dependency.management)
  id("gg.jte.gradle") version "3.1.16"
}

base {
  archivesName = "data-jdbc"
  group = "com.example.data.jdbc"
}


sourceSets {
  main {
    resources.srcDirs("resources")
  }
}


configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}


dependencies {
  implementation(project(":domain"))
  implementation(project(":scenarios"))
  implementation(project(":scenarios:inbound"))
  implementation(project(":rest"))
  implementation(project(":data-jdbc:persistence"))
  implementation(project(":events-journal"))
  implementation(project(":tsid"))

  implementation("org.springframework.modulith:spring-modulith-starter-core") // TODO move to toml
  implementation("org.springframework.modulith:spring-modulith-events-jackson")
  implementation("org.springframework.modulith:spring-modulith-events-jdbc")

  implementation(libs.hypersistence.tsid)
  implementation(libs.spring.boot.starter.web)
  implementation(libs.spring.boot.starter)
  implementation(libs.spring.boot.starter.data.jdbc)

  runtimeOnly(libs.h2)


  implementation("gg.jte:jte-spring-boot-starter-3:3.1.16")
  implementation("io.github.wimdeblauwe:htmx-spring-boot:4.0.1")

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

jte {
  binaryStaticContent = true
  sourceDirectory.set(kotlin.io.path.Path("resources/jte"))
  generate()
}
