pluginManagement {
    repositories {
        mavenCentral()
        mavenLocal()
    }
}

rootProject.name = "one-to-many-example-data"


include(":plain-jdbc")
include(":data-jdbc")
include(":data-jpa")

include(":scenarios")
include(":scenarios:inbound")
include(":scenarios:outbound")
include(":scenarios:usecases")