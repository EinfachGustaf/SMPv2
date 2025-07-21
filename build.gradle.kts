plugins {
    kotlin("jvm") version "2.1.21"
    id("com.gradleup.shadow") version "9.0.0-rc1"
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.18"
}

group = "live.einfachgustaf"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    paperweight.foliaDevBundle("1.21.6-R0.1-SNAPSHOT")

    implementation("org.jetbrains.exposed", "exposed-core", "1.0.0-beta-4")
    implementation("org.jetbrains.exposed", "exposed-dao", "1.0.0-beta-4")
    implementation("org.jetbrains.exposed", "exposed-jdbc", "1.0.0-beta-4")
    implementation("com.h2database:h2:2.3.232") // TODO: remove!!! only for testing!
}

kotlin {
    jvmToolchain(21)
}

tasks {
    build {
        dependsOn(shadowJar)
    }
    assemble {
        dependsOn(reobfJar)
    }
}