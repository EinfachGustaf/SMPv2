package live.einfachgustaf.smp

import org.bukkit.plugin.java.JavaPlugin

class Entrypoint: JavaPlugin() {

    companion object {
        lateinit var instance: Entrypoint; private set
    }

    override fun onLoad() {
        instance = this
    }
}