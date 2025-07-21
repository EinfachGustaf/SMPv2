package live.einfachgustaf.smp.core.config

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

/**
 * High-level wrapper for a YAML configuration file.
 * Offers simplified loading, saving, and access methods.
 *
 * @property file The YAML file on disk.
 * @property config The loaded configuration instance.
 */
class YamlConfigurationWrapper(private val file: File) {

    val config: YamlConfiguration = YamlConfiguration()

    /**
     * Loads the configuration from disk if the file exists.
     * Otherwise, creates a new empty configuration.
     */
    fun load() {
        if (file.exists()) {
            config.load(file)
        } else {
            file.parentFile.mkdirs()
            file.createNewFile()
            save() // Save default empty config
        }
    }

    /**
     * Saves the configuration to disk.
     */
    fun save() {
        config.save(file)
    }

    /**
     * Sets a value in the configuration.
     *
     * @param path The path (e.g. "general.prefix").
     * @param value The value to set.
     */
    fun set(path: String, value: Any?) {
        config.set(path, value)
        save()
    }

    /**
     * Gets a value from the configuration with type safety.
     *
     * @param T The expected return type.
     * @param path The path to the value.
     * @return The value, or null if not found.
     */
    inline fun <reified T> get(path: String): T? {
        return config.get(path) as? T
    }

    /**
     * Checks if the given path exists in the config.
     */
    fun contains(path: String): Boolean = config.contains(path)
}
