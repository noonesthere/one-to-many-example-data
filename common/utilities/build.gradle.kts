plugins {
  id("java-library")
}


base {
  archivesName = "common-utilities"
  group = "com.example.common.utilities"
}

dependencies {
  api(libs.annotation.api) // non null nullable
}
