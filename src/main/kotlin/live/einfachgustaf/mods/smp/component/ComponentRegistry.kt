package live.einfachgustaf.mods.smp.component

/**
 * A registry for components.
 */
class ComponentRegistry {

    /**
     * The list of components to register.
     */
    val components = mutableListOf<SMPComponent>()

    /**
     * Register a component.
     * @param component The component to register.
     */
    fun register(component: SMPComponent) {
        components.add(component)
    }

    /**
     * Process components.
     * @param action The action to perform on each component.
     */
    private fun processComponents(action: (SMPComponent) -> Unit) {
        val toLoad = components.toMutableList()

        while (toLoad.isNotEmpty()) {
            val iterator = toLoad.iterator()
            while (iterator.hasNext()) {
                val component = iterator.next()
                val dependencies = component.getInfo()?.dependencies?.map { it.componentClass } ?: emptyList()
                if (dependencies.all { dep -> components.any { it::class == dep } }) {
                    action(component)
                    iterator.remove()
                }
            }
        }
    }

    /**
     * Load components.
     */
    fun loadComponents() {
        processComponents { it.load() }
    }

    /**
     * Start components.
     */
    fun postStartComponents() {
        processComponents { it.postStart() }
    }

    /**
     * Stop components.
     */
    fun preStopComponents() {
        processComponents { it.preStop() }
    }
}