package live.einfachgustaf.smp.utils

import live.einfachgustaf.smp.Entrypoint
import org.bukkit.plugin.ServicePriority
import org.bukkit.plugin.ServicesManager
import org.bukkit.plugin.java.JavaPlugin
import kotlin.reflect.KClass

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

/**
 * Retrieves a simple registered service from the ServicesManager by its Kotlin class type.
 *
 * This function uses reified generics to avoid passing the Java class manually.
 *
 * @param T The type of the service to retrieve.
 * @param clazz The Kotlin class reference of the service type.
 * @return An instance of the registered service if available, or `null` otherwise.
 *
 * Example usage:
 * val myService = servicesManager.getSimple<MyService>(MyService::class)
 */
inline fun <reified T : Any> ServicesManager.getSimple(clazz: KClass<T>): T? {
    return getRegistration(T::class.java)?.provider
}


/**
 * Shortcut to access the global [ServicesManager] from the running Bukkit server.
 *
 * This provides access to the service registry used to register and retrieve
 * service providers (e.g. permission plugins, economy systems, etc.).
 *
 * Equivalent to: `Bukkit.getServer().getServicesManager()`
 *
 * @see org.bukkit.plugin.ServicesManager
 */
val servicesManager: ServicesManager
    get() = Entrypoint.instance.server.servicesManager
