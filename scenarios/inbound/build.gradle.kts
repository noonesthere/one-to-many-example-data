base {
  archivesName = "inbound"
  group = "com.example.scenarios.inbound"
}


dependencies {
  implementation(project(":domain"))
  implementation(project(":scenarios:dto"))
}


