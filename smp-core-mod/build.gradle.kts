plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.userdev)
}

repositories {
    maven("https://maven.fabricmc.net/")
}

dependencies {
    paperweight.paperDevBundle(libs.versions.paper)
    libs.bundles.igniteMod
}