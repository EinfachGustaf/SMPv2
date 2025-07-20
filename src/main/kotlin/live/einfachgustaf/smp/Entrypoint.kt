package live.einfachgustaf.smp

import live.einfachgustaf.smp.feature.FeatureContext
import live.einfachgustaf.smp.feature.FeatureManager
import org.bukkit.plugin.java.JavaPlugin

class Entrypoint: JavaPlugin() {

    lateinit var featureManager: FeatureManager

    override fun onLoad() {
        instance = this
    }

    override fun onEnable() {
        featureManager = FeatureManager(FeatureContext(this)).apply {
            // register all features
            // ...
            enableAll()
        }
    }

    override fun onDisable() {
        featureManager.disableAll()
    }

    companion object {
        lateinit var instance: Entrypoint
            private set
    }
}