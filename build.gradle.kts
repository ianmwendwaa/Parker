plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    kotlin("jvm") version "2.4.10"
}
dependencies {
    implementation(kotlin("stdlib-jdk8"))
}
repositories {
    mavenCentral()
}
kotlin {
    jvmToolchain(8)
}