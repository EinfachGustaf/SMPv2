@file:UseSerializers(AnySerializer::class)

package live.einfachgustaf.mods.smp.component.properties

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import live.einfachgustaf.mods.smp.serializer.AnySerializer
import kotlin.collections.set
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * A file data class for properties.
 */
@Serializable
data class Properties(val properties: MutableMap<String, Any?> = mutableMapOf())

/**
 * A delegate for properties.
 * @param defaultValue The default value of the property.
 */
class PropertyDelegate<T>(private val defaultValue: T) : ReadWriteProperty<PropertyContainer, T> {
    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: PropertyContainer, property: KProperty<*>): T {
        thisRef.loadProperties() // Ensure properties are loaded before accessing them
        val value = thisRef.properties.properties[property.name] as? T
        return if (value != null) {
            value
        } else {
            thisRef.properties.properties[property.name] = defaultValue
            thisRef.saveProperties()
            defaultValue
        }
    }

    override fun setValue(thisRef: PropertyContainer, property: KProperty<*>, value: T) {
        thisRef.loadProperties() // Ensure properties are loaded before setting them
        thisRef.properties.properties[property.name] = value
        thisRef.saveProperties()
    }
}