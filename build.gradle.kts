val javaVersion = 21
val silkVersion = "1.10.7"

plugins {
    val kotlinVersion = "2.0.0"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    id("fabric-loom") version "1.8.10"
}

group = "live.einfachgustaf"
version = "0.1+1.21.1"

repositories {
    mavenCentral()
    maven("https://repo.kord.dev/snapshots") // kord
    maven("https://snapshots-repo.kordex.dev") // fox kordex (kord extensions)
}

dependencies {
    minecraft("com.mojang:minecraft:1.21.1")
    mappings(loom.officialMojangMappings())

    modImplementation("net.fabricmc:fabric-loader:0.16.5")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.105.0+1.21.1")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.11.0+kotlin.2.0.0")

    modImplementation("net.silkmc:silk-core:$silkVersion")

    include(implementation("me.obsilabor", "alert", "1.0.8"))
    include(implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")!!)
    include(implementation("com.squareup.okhttp3:okhttp:4.12.0")!!)

    // Database
    include(implementation("org.mongodb:mongodb-driver-kotlin-coroutine:5.2.1")!!)
    include(implementation("org.mongodb:bson-kotlinx:5.2.1")!!)

    // Discord
    include(implementation("dev.kord:kord-core:0.15.0")!!)
    include(implementation("dev.kordex:kord-extensions:2.3.1-SNAPSHOT")!!)
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(javaVersion)
    }
    processResources {
        val properties = mapOf("version" to project.version)
        inputs.properties(properties)
        filesMatching("fabric.mod.json") { expand(properties) }
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs = listOf("-Xjdk-release=$javaVersion", "-Xskip-prerelease-check")
        jvmToolchain(javaVersion)
    }
}