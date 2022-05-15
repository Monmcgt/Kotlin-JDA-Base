package me.monmcgt.code.commands.impl

import me.monmcgt.code.commands.CommandAbstract
import me.monmcgt.code.commands.CommandInfo
import me.monmcgt.code.commands.RegisterCommand

@RegisterCommand
@CommandInfo("ping", "responds with pong", autoDeferReply = false)
class Ping : CommandAbstract() {
    override fun onSlashCommandInteraction() {
        sendEmphemralMessage("pong")
    }
}