base {
  archivesName = "rest"
  group = "com.example.rest"
}


configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}


dependencies {
  implementation(project(":common:utilities"))
  implementation(project(":domain"))
  implementation(project(":scenarios:inbound"))
  implementation(project(":scenarios:dto"))

  implementation(libs.hypersistence.tsid)

  implementation(libs.spring.boot.starter.web)

  annotationProcessor(libs.spring.boot.configuration.processor)

  testImplementation(libs.spring.boot.starter.test)
  testRuntimeOnly(libs.junit.platform.launcher)
}

