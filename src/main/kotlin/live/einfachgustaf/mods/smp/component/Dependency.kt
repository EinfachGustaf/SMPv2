package live.einfachgustaf.mods.smp.component

import kotlin.reflect.KClass

/**
 * An annontation to provide information about a dependency.
 * @param componentClass The class of the component that this component depends on.
 */
annotation class Dependency(
    val componentClass: KClass<out SMPComponent>
)
