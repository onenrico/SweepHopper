package me.onenrico.sweephopper.ext

import org.bukkit.Bukkit
import org.bukkit.Location

fun Location.flatString(): String {
    return "${world?.name}#$x#$y#$z#$yaw#$pitch"
}

fun Location.centered(): Location {
    x += .5
    z += .5
    return this
}

fun String.toLocation(): Location {
    val l = split("#".toRegex())
    return Location(
        Bukkit.getWorld(l[0]),
        l[1].toDouble(),
        l[2].toDouble(),
        l[3].toDouble(),
        l[4].toFloat(),
        l[5].toFloat()
    )
}