package me.onenrico.sweephopper.particle

import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.World
import org.bukkit.util.Vector

object TrailParticle {
    fun spawn(world: World, from: Vector, to: Vector, particle: Particle) {
        to.add(Vector(0.0,-0.35,0.0))
        val distance = to.clone().subtract(from)
        val direction = distance.clone().normalize()
        val space = direction.clone().multiply(.9)
        val source = Location(world, from.x, from.y, from.z)

        val particleSpeed = direction.clone().multiply(distance.length() / 19)
        var max = 1
        while (max >= 1) {
            world.spawnParticle(particle, source, 0, particleSpeed.x, particleSpeed.y, particleSpeed.z)
            source.add(space)
            max--
        }

    }
}