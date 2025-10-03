
base {
  archivesName = "data-jdbc-persistence"
  group = "com.example.data.jdbc.persistence"
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
  implementation(project(":scenarios:outbound"))

  implementation(libs.spring.boot.starter.jdbc)
  runtimeOnly(libs.h2)

  annotationProcessor(libs.spring.boot.configuration.processor)

  testImplementation(libs.spring.boot.starter.test)
  testRuntimeOnly(libs.junit.platform.launcher)
}
