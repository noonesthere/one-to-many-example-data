

base {
  archivesName = "data-jdbc-log"
  group = "com.example.data.jdbc.log"
}


sourceSets {
  main {
    resources.srcDirs("db")
  }
}


configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}


dependencies {
  implementation(project(":domain"))
//
  implementation(libs.spring.context)
//  implementation(libs.spring.tx)

  implementation(libs.jackson.databind)

  implementation(libs.spring.boot.starter.data.jdbc)
  implementation("org.springframework.modulith:spring-modulith-events-api:1.4.1")
  runtimeOnly(libs.h2)


  annotationProcessor(libs.spring.boot.configuration.processor)

  testImplementation(libs.spring.boot.starter.test)
  testRuntimeOnly(libs.junit.platform.launcher)
}
