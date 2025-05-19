plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.userdev) apply false
}

allprojects {
    repositories {
        mavenCentral()
    }
}