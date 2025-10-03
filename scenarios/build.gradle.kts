base {
  archivesName = "scenarios"
  group = "com.example.scenarios"
}


dependencies {
  implementation(libs.inject.api)
  implementation(project(":common:types"))
  implementation(project(":domain"))
  implementation(project(":scenarios:dto"))
  implementation(project(":scenarios:inbound"))
  implementation(project(":scenarios:outbound"))
}


