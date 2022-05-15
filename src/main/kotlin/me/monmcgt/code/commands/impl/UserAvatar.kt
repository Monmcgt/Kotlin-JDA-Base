package me.monmcgt.code.commands.impl

import me.monmcgt.code.commands.CommandAbstract
import me.monmcgt.code.commands.CommandInfo
import me.monmcgt.code.commands.RegisterCommand

@RegisterCommand
@CommandInfo(name = "useravatar", description = "Get the avatar of a user")
class UserAvatar : CommandAbstract() {
    override fun onSlashCommandInteraction() {
        val member = event.member
        if (member == null) {
            sendEmphemralMessage("Something went wrong, please try again later")
            return
        }
        sendEmphemralMessage(member.user.effectiveAvatarUrl)
    }
}