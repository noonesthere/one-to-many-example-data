plugins {
  id("java-library")
}


base {
  archivesName = "common-events"
  group = "com.example.common.events"
}


dependencies {
  implementation(project(":common:types"))
}
