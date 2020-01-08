package me.onenrico.sweephopper.ticker

import me.onenrico.sweephopper.ext.centered
import me.onenrico.sweephopper.particle.TrailParticle
import me.onenrico.sweephopper.repository.LocalStorage
import org.bukkit.Particle
import org.bukkit.entity.EntityType
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.util.Vector

class SuckTicker() : BukkitRunnable() {

    override fun run() {
        LocalStorage.hoppers.map { it.getHopper() }.forEach {
            it?.let { hopper ->
                val world = hopper.world
                world.getNearbyEntities(hopper.location, 5.0, 5.0, 5.0)
                    .filter { entity -> entity.type == EntityType.DROPPED_ITEM }
                    .forEach { drop ->
                        val target = hopper.location.centered().toVector().add(Vector(0.0,1.25,0.0))
                        val current = drop.location.toVector()
                        TrailParticle.spawn(world,current.clone(),target.clone(), Particle.FLAME)
                        val direction = target.subtract(current).normalize()
                        val ydirection = direction.y
                        val velocity = direction.clone().multiply(.03)
                        velocity.y = ydirection
                        drop.velocity = velocity
                        if(!drop.isCustomNameVisible){
                            drop.customName = "Help Me...!"
                            drop.isCustomNameVisible = true
                        }
                    }
            }
        }
    }

}