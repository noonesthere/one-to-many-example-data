plugins {
  id("java-library")
}

base {
  archivesName = "outbound"
  group = "com.example.scenarios.outbound"
}


dependencies {
  implementation(project(":domain"))
  api(project(":scenarios:dto"))
}



