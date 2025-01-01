package live.einfachgustaf.mods.smp.component

import live.einfachgustaf.mods.smp.component.properties.PropertyContainer
import kotlin.io.path.Path

/**
 * An annotation to provide information about a component.
 * @param name The name of the component.
 * @param description A description of the component.
 */
abstract class SMPComponent(id: String) : PropertyContainer(Path("config/components/$id.json")) {

    /**
     * Whether the component is loaded or not.
     */
    var isLoaded: Boolean = false; internal set

    /**
     * The load function is called on the mod initialization.
     */
    open fun load() = Unit

    /**
     * The start function is called after the server is started
     */
    open fun postStart() = Unit

    /**
     * The stop function is called before the server is stopped
     */
    open fun preStop() = Unit

    /**
     * The requirements for this component to be loaded.
     */
    abstract val requirements: MutableMap<String, () -> Boolean>

    /**
     * The information about this component.
     */
    fun getInfo() = this::class.annotations.find { it is ComponentInfo } as ComponentInfo?

    init {
        if (getInfo() == null) {
            throw IllegalArgumentException("Component ${this::class.simpleName} is missing a ComponentInfo annotation!")
        }
    }
}