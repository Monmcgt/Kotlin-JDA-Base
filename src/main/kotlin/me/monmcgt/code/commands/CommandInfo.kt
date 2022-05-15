package me.monmcgt.code.commands

import me.monmcgt.code.enums.SlashCommandType

annotation class CommandInfo(
    val name: String,
    val description: String,
    val slashCommandType: SlashCommandType = SlashCommandType.GUILD_ALL,
    val guilds: LongArray = [],
    val autoDeferReply: Boolean = true,
    val autoRegisterSlashCommand: Boolean = true,
    val autoRegisterClass: Boolean = false,
)
