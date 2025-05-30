plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.userdev)
    alias(libs.plugins.shadow)
}

repositories {
    maven("https://maven.fabricmc.net/")
}

dependencies {
    paperweight.paperDevBundle(libs.versions.paper)
    compileOnly(libs.bundles.igniteMod)
    implementation(libs.alert)
}