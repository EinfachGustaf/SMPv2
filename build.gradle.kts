plugins {
    kotlin("jvm") version "2.1.21"
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.17"
}

group = "live.einfachgustaf"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    paperweight.foliaDevBundle("1.21.6-R0.1-SNAPSHOT")
}

kotlin {
    jvmToolchain(21)
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }
}