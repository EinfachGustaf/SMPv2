import xyz.jpenilla.resourcefactory.paper.paperPluginYaml

plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.userdev)
    alias(libs.plugins.resourceFactory)
}

dependencies {
    paperweight.paperDevBundle("1.21.5-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

tasks {
    compileJava {
        options.release = 21
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
    }
}

paperPluginYaml {
    main = "live.einfachgustaf.smp.core.Entrypoint"
    listOf("DinoMarlir", "Fogknight").forEach { authors.add(it) }
    apiVersion = "1.21.5"
    version = project.version.toString()
}