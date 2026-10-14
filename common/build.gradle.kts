import com.varabyte.kobweb.gradle.library.util.configAsKobwebLibrary

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.library)
    alias(libs.plugins.kobwebx.markdown)
    alias(libs.plugins.kotlinx.serialization)
}

group = "org.example.parker"
version = "1.0-SNAPSHOT"


kotlin {
    configAsKobwebLibrary(includeServer = true)

    sourceSets {
        jsMain.dependencies {
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.compose.runtime)
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
            implementation("io.github.cdimascio:dotenv-kotlin:6.5.1")
        }
        jvmMain.dependencies {
            implementation(libs.kobweb.api)
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
            implementation("io.github.cdimascio:dotenv-kotlin:6.5.1")
        }
    }
}
