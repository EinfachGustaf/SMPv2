package live.einfachgustaf.smp.utils

import live.einfachgustaf.smp.Entrypoint
import org.bukkit.plugin.ServicePriority
import org.bukkit.plugin.ServicesManager
import org.bukkit.plugin.java.JavaPlugin

/**
 * Registers a service implementation instance with the Bukkit ServicesManager using reified type inference.
 *
 * @param T The type of the service interface.
 * @param instance The instance of the service implementation to register.
 * @param plugin The plugin registering the service. Defaults to [Entrypoint.instance].
 * @param priority The priority of the service registration. Defaults to [ServicePriority.Normal].
 */
inline fun <reified T : Any> ServicesManager.registerSimple(
    instance: T,
    plugin: JavaPlugin = Entrypoint.instance,
    priority: ServicePriority = ServicePriority.Normal
) {
    register(T::class.java, instance, plugin, priority)
}
