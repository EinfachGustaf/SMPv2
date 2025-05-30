plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.userdev)
}

dependencies {
    paperweight.paperDevBundle(libs.versions.paper)
    compileOnly(project(":smp-core"))
}