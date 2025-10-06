plugins {
  id("java-library")
}

base {
  archivesName = "domain"
  group = "com.example.domain"
}


dependencies {
  implementation(project(":common:utilities"))
  api(project(":common:types"))
}


