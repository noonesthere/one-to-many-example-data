plugins {
  id("java-library")
}


base {
  archivesName = "common-types"
  group = "com.example.common.types"
}


dependencies {
  api(libs.annotation.api) // non null nullable
  api(libs.vavr)
  api(libs.inject.api)
}
