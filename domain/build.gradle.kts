plugins {
  id("java-library")
}

base {
  archivesName = "domain"
  group = "com.example.domain"
}


dependencies {
  api(project(":common:types"))
}


