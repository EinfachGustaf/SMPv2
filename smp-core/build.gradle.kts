import xyz.jpenilla.resourcefactory.paper.paperPluginYaml

plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.userdev)
    alias(libs.plugins.resourceFactory)
}

dependencies {
    paperweight.paperDevBundle(libs.versions.paper)

    compileOnly(libs.alert)
    compileOnly(project(":smp-core-mod"))
}

paperPluginYaml {
    main = "live.einfachgustaf.smp.plugin.core.Entrypoint"
    listOf("DinoMarlir", "Fogknight").forEach { authors.add(it) }
    apiVersion = "1.21.5"
    version = project.version.toString()
}