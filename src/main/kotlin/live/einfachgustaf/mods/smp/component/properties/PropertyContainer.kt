package live.einfachgustaf.mods.smp.component.properties

import kotlinx.serialization.encodeToString
import live.einfachgustaf.mods.smp.utils.json
import java.nio.file.Path

/**
 * A container for properties.
 */
abstract class PropertyContainer(val path: Path) {

    /**
     * The properties of this container.
     */
    val properties = Properties()

    /**
     * The cached properties.
     */
    private var cachedProperties: Properties? = null

    /**
     * Save the properties to the file.
     */
    fun saveProperties() {
        val file = path.toFile()
        if (!file.exists()) {
            file.parentFile.mkdirs()
            file.createNewFile()
        }
        file.writeText(json.encodeToString(properties))
    }

    /**
     * Load the properties from the file.
     */
    fun loadProperties() {
        if (cachedProperties == null) {
            val file = path.toFile()
            if (file.exists()) {
                cachedProperties = json.decodeFromString<Properties>(file.readText())
            } else {
                cachedProperties = Properties()
            }
        }
        properties.properties.putAll(cachedProperties!!.properties)
    }
}