package me.monmcgt.code.commands.impl

import me.monmcgt.code.commands.CommandAbstract
import me.monmcgt.code.commands.CommandInfo
import me.monmcgt.code.commands.RegisterCommand

@RegisterCommand
@CommandInfo(name = "ping", description = "responds with pong")
class Ping : CommandAbstract() {
    override fun onSlashCommandInteraction() {
        sendEmphemralMessage("Pong!")
    }
}