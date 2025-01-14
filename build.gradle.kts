val javaVersion = 21
val silkVersion = "1.10.7"

plugins {
    val kotlinVersion = "2.0.0"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    id("org.jetbrains.dokka") version kotlinVersion
    id("fabric-loom") version "1.8.10"
}

group = "live.einfachgustaf"
version = "0.1+1.21.1"

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:1.21.1")
    mappings(loom.officialMojangMappings())

    modImplementation("net.fabricmc:fabric-loader:0.16.5")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.105.0+1.21.1")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.11.0+kotlin.2.0.0")

    modImplementation("net.silkmc:silk-core:$silkVersion")

    include(implementation("me.obsilabor", "alert", "1.0.8"))
    include(implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")!!)

    // Database
    include(implementation("org.mongodb:mongodb-driver-kotlin-coroutine:5.3.0")!!)
    include(implementation("org.mongodb:bson-kotlinx:5.3.0")!!)

    // Discord
    include(implementation("dev.kord:kord-core:0.15.0")!!)
    modImplementation("net.kyori:adventure-platform-fabric:5.14.2")
    include(implementation("net.kyori:adventure-text-minimessage:4.17.0")!!)
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