package me.onenrico.sweephopper.model

import me.onenrico.sweephopper.ext.flatString
import me.onenrico.sweephopper.ext.toLocation
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Hopper

data class SweepHopper(val loc: Location) : Model {

    private var cacheHopper: Hopper? = null
    fun getHopper(): Hopper? {
        if (cacheHopper == null) {
            loc.block.let {
                if (it.type == Material.HOPPER) {
                    cacheHopper = it.state as Hopper
                }
            }
        }
        return cacheHopper
    }

    override fun serialize(): String {
        return loc.flatString()
    }

    companion object {
        fun deserialize(serialized: String): SweepHopper {
            return SweepHopper(serialized.toLocation())
        }
    }
}