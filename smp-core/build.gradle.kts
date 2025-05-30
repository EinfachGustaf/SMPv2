plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.userdev)
}

dependencies {
    paperweight.paperDevBundle(libs.versions.paper)

    compileOnly(libs.alert)
    compileOnly(project(":smp-core-mod"))
}