package me.monmcgt.code.config

import me.monmcgt.code.gson
import java.io.File
import kotlin.system.exitProcess

private val configFile = File("config.json")

class ConfigManager private constructor() {
    companion object {
        @JvmStatic
        private val INSTANCE = ConfigManager()

        @JvmStatic
        fun getInstance() = INSTANCE
    }

    var token = ""

    fun init() {
        if (!configFile.exists()) {
            configFile.createNewFile()
            configFile.bufferedWriter().use {
                it.write(gson.toJson(ConfigFile("")))
            }
        } else {
            val configFile = gson.fromJson(configFile.bufferedReader(), ConfigFile::class.java)
            if (configFile.token.isEmpty()) {
                System.err.println("Token is empty!")
                exitProcess(1)
            } else {
                this.token = configFile.token
            }
        }
    }
}