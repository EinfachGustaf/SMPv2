package live.einfachgustaf.smp

import live.einfachgustaf.smp.core.feature.FeatureContext
import live.einfachgustaf.smp.core.feature.FeatureManager
import live.einfachgustaf.smp.features.SpawnFeature
import live.einfachgustaf.smp.utils.registerSimple
import live.einfachgustaf.smp.utils.servicesManager
import org.bukkit.plugin.java.JavaPlugin

class Entrypoint: JavaPlugin() {

    private lateinit var featureManager: FeatureManager

    override fun onLoad() {
        instance = this

        featureManager = FeatureManager(FeatureContext(this)).apply {
            // register all features
            register(SpawnFeature())
        }
        servicesManager.registerSimple(featureManager)
    }

    override fun onEnable() {
        featureManager.enableAll()
    }

    override fun onDisable() {
        featureManager.disableAll()
    }

    companion object {
        lateinit var instance: Entrypoint
            private set
    }
}