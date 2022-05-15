package me.monmcgt.code

import me.monmcgt.code.commands.CommandManager
import me.monmcgt.code.config.ConfigManager
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder

var jda: JDA? = null

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val configManager = ConfigManager.getInstance()
            configManager.init()
            jda = JDABuilder.createLight(configManager.token).build().awaitReady()

            val commandManager = CommandManager.getInstance()
            commandManager.init()
            commandManager.getAllRegisteredCommands().forEach {
                println("Registered command: ${it::class.java.simpleName}")
            }
        }
    }
}