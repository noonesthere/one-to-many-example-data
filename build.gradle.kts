//import org.gradle.accessors.dm.LibrariesForLibs
//
//val libx = the<LibrariesForLibs>()
plugins {
  java
  alias(libs.plugins.spring.boot) apply false
  alias(libs.plugins.spring.dependency.management) apply false
}


subprojects {


  configurations.all {
    resolutionStrategy {
      eachDependency {
        requested.version?.contains("snapshot", true)?.let {
          if (it) {
            throw GradleException("Snapshot found: ${requested.name} ${requested.version}")
          }
        }
      }
    }
  }


  apply {
    plugin("java")
//        plugin(libx.plugins.spring.boot.get().pluginId)
//        plugin(libx.plugins.spring.dependency.management.get().pluginId)
  }


  sourceSets {
    main {
      java.setSrcDirs(listOf("src"))
      resources.setSrcDirs(listOf("src")).exclude("**/*.java")
    }
    test {
      java.setSrcDirs(listOf("test"))
      resources.setSrcDirs(listOf("test")).exclude("**/*.kava")
    }
  }


  repositories {
    mavenCentral()
    mavenLocal()
  }


  java {
    toolchain {
      languageVersion = JavaLanguageVersion.of(21)
    }
  }


  tasks {
    withType<JavaCompile> {
      options.compilerArgs.add("-Xlint:all")
      targetCompatibility = JavaVersion.VERSION_21.toString()
    }

    withType<Jar> {
      duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    withType<Test> {
      useJUnitPlatform()

      testLogging {
        events(
          org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
          org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
          org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
        )
        showStandardStreams = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
      }
    }
  }

}
