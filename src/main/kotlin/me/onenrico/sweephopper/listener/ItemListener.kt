package me.onenrico.sweephopper.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ItemSpawnEvent

class ItemListener : Listener{
    @EventHandler
    fun onItemSpawn(e: ItemSpawnEvent){
        print(e.entity)
    }
}