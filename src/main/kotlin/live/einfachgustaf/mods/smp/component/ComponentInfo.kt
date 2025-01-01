package live.einfachgustaf.mods.smp.component

/**
 * An annotation to provide information about a component.
 * @param name The name of the component.
 * @param description A description of the component.
 */
annotation class ComponentInfo(
    val name: String,
    val description: String,
    val dependencies: Array<Dependency> = []
)