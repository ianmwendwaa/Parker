pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        mavenLocal()
        google()
    }
}

// The following block registers dependencies to enable Kobweb snapshot support. It is safe to delete or comment out
// this block if you never plan to use them.
gradle.settingsEvaluated {
    fun RepositoryHandler.kobwebSnapshots() {
        maven("https://central.sonatype.com/repository/maven-snapshots/") {
            mavenContent {
                includeGroupByRegex("com\\.varabyte\\.kobweb.*")
                snapshotsOnly()
            }
        }
    }

    pluginManagement.repositories { kobwebSnapshots() }
    dependencyResolutionManagement.repositories { kobwebSnapshots() }
}

rootProject.name = "parker"

include(":site")
include(":common")
