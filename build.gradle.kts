plugins {
    kotlin("jvm") version "2.1.21"
}

group = "live.einfachgustaf"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("dev.folia", "folia-api", "1.21.6-R0.1-SNAPSHOT")
}

kotlin {
    jvmToolchain(21)
}