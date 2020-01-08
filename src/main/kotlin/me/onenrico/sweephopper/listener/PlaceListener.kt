package me.onenrico.sweephopper.listener

import me.onenrico.sweephopper.ext.centered
import me.onenrico.sweephopper.model.SweepHopper
import me.onenrico.sweephopper.repository.LocalStorage
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent

class PlaceListener : Listener {

    @EventHandler
    fun onPlacehopper(e: BlockPlaceEvent) {
        e.blockPlaced.let {
            if (it.type == Material.HOPPER) {
                LocalStorage.save(SweepHopper(it.location.centered()))
            }
        }
    }

    @EventHandler
    fun onBreakhopper(e: BlockBreakEvent) {
        e.block.let {
            if (it.type == Material.HOPPER) {
                LocalStorage.remove(SweepHopper(it.location.centered()))
            }
        }
    }

}