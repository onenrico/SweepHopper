package me.onenrico.sweephopper.repository

import me.onenrico.sweephopper.main.Core
import me.onenrico.sweephopper.model.Model
import me.onenrico.sweephopper.model.SweepHopper
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

object LocalStorage {

    private lateinit var file: File
    private lateinit var database: FileConfiguration
    private lateinit var currentRaw: MutableList<String>
    lateinit var hoppers: MutableList<SweepHopper>

    fun load(context: Core) {
        if(!context.dataFolder.exists())context.dataFolder.mkdirs()
        file = File(context.dataFolder, "data.hop")
        if (!file.exists()) {
            file.createNewFile()
        }
        database = YamlConfiguration.loadConfiguration(file)
        currentRaw = database.getStringList("data")
        hoppers = currentRaw.map { SweepHopper.deserialize(it) }.toMutableList()
        context.dataLoaded()
    }

    fun save(model: Model) {
        currentRaw.add(model.serialize())
        hoppers.add(model as SweepHopper)
        database.set("data", currentRaw)
        database.save(file)
    }

    fun remove(model: Model) {
        currentRaw.remove(model.serialize())
        hoppers.remove(model as SweepHopper)
        database.set("data", currentRaw)
        database.save(file)
    }

}