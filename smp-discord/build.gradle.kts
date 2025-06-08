plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.userdev)
    alias(libs.plugins.shadow)
}

dependencies {
    paperweight.paperDevBundle(libs.versions.paper)
    compileOnly(project(":smp-core"))
    implementation(libs.jda)
}

// TODO: use Paper Dependency Loader
tasks {
    build {
        dependsOn(shadowJar)
    }
}