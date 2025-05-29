plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.userdev) apply false
}

allprojects {
    group = "live.einfachgustaf.smp"
    version = "1.0"

    repositories {
        mavenCentral()
    }
}