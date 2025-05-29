import xyz.jpenilla.resourcefactory.paper.paperPluginYaml

plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.userdev)
    alias(libs.plugins.resourceFactory)
}

dependencies {
    paperweight.paperDevBundle("1.21.5-R0.1-SNAPSHOT")
}

paperPluginYaml {
    main = "live.einfachgustaf.smp.core.Entrypoint"
    listOf("DinoMarlir", "Fogknight").forEach { authors.add(it) }
    apiVersion = "1.21.5"
    version = project.version.toString()
}