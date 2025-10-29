
plugins {
  id("java-library")
}

base {
  archivesName = "tsid"
  group = "com.example.tsid"
}

dependencies {
  implementation(project(":domain"))

  implementation(libs.inject.api)
  implementation(libs.hypersistence.tsid)

  testImplementation(libs.junit.jupiter)
  testRuntimeOnly(libs.junit.platform.launcher)
}


