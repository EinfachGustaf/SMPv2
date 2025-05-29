plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.userdev) apply false
}

allprojects {
    group = "live.einfachgustaf.smp"
    version = "1.0"

    apply(plugin = "java")

    repositories {
        mavenCentral()
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
}