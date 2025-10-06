plugins {
  id("java-library")
}

base {
  archivesName = "inbound"
  group = "com.example.scenarios.inbound"
}


dependencies {
  implementation(project(":domain"))
  api(project(":scenarios:dto"))
}


