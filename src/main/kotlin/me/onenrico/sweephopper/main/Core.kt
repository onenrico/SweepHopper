package me.onenrico.sweephopper.main

import me.onenrico.sweephopper.listener.ItemListener
import me.onenrico.sweephopper.listener.PlaceListener
import me.onenrico.sweephopper.repository.LocalStorage
import me.onenrico.sweephopper.ticker.SuckTicker
import org.bukkit.plugin.java.JavaPlugin

class Core : JavaPlugin() {

    private var suckTicker: SuckTicker? = null

    override fun onEnable() {
        server.pluginManager.registerEvents(PlaceListener(), this)
        server.pluginManager.registerEvents(ItemListener(), this)
        LocalStorage.load(this)
    }

    override fun onDisable() {
        suckTicker?.cancel()
    }

    fun dataLoaded() {
        suckTicker = SuckTicker()
        suckTicker?.runTaskTimer(this, 2, 1)
    }


}