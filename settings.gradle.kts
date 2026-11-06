rootProject.name = "yumii"

include(
    "yumii-platform",
    "yumii-domain",
    "yumii-service",
    "yumii-persistence",
    "yumii-endpoint",
    "yumii-application",
    "yumii-webui",
)

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
