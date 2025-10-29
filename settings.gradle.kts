rootProject.name = "one-to-many-example-data"

include(":common:types")
include(":common:events")
include(":common:utilities")

include(":domain")
include(":rest")

include(":scenarios")
include(":scenarios:dto")
include(":scenarios:inbound")
include(":scenarios:outbound")

include(":data-jdbc")
include(":data-jdbc:persistence")
include(":events-journal")

include(":tsid")
