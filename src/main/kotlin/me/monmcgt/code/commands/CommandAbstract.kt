package me.monmcgt.code.commands

import me.monmcgt.code.enums.SlashCommandType
import me.monmcgt.code.jda
import me.monmcgt.code.util.queueIgnoreException
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.OptionData

abstract class CommandAbstract : CommandUtility() {
    protected val optionDataArray = arrayOf<OptionData>()

    var commandInfo: CommandInfo

    var commandName: String
    var commandDescription: String

    init {
        commandInfo = javaClass.getAnnotation(CommandInfo::class.java) ?: throw IllegalArgumentException("Command class must be annotated with @CommandInfo")
        commandName = commandInfo.name
        commandDescription = commandInfo.description
    }

    fun execute(event: SlashCommandInteractionEvent) {
        this.event = event
        if (commandInfo.autoDeferReply) {
            event.deferReply(true).queue()
        }
        onSlashCommandInteraction()
    }

    fun load() {
        if (commandInfo.autoRegisterSlashCommand) {
            when (commandInfo.slashCommandType) {
                SlashCommandType.ALL -> {
                    val upsertCommand = jda!!.upsertCommand(commandName, commandDescription)
                    if (optionDataArray.isNotEmpty()) {
                        upsertCommand.addOptions(*optionDataArray)
                    }
                    upsertCommand.queueIgnoreException()
                }
                SlashCommandType.GUILD_ALL -> {
                    jda!!.guilds.forEach {
                        val upsertCommand = it.upsertCommand(commandName, commandDescription)
                        if (optionDataArray.isNotEmpty()) {
                            upsertCommand.addOptions(*optionDataArray)
                        }
                        upsertCommand.queueIgnoreException()
                        println("Registered command $commandName in guild ${it.name}")
                    }
                }
                SlashCommandType.GUILD_SPECIFIC -> {
                    commandInfo.guilds.forEach {
                        val upsertCommand = jda!!.getGuildById(it)?.upsertCommand(commandName, commandDescription)
                        if (optionDataArray.isNotEmpty()) {
                            upsertCommand?.addOptions(*optionDataArray)
                        }
                        upsertCommand?.queueIgnoreException()
                    }
                }
            }
        }
    }

    fun isThisClass(event: SlashCommandInteractionEvent): Boolean {
        if (event.name != commandName) {
            return false
        }
        val options = event.options
        if (options.size != optionDataArray.size) {
            return false
        }
        for (i in 0 until options.size) {
            try {
                val option = options[i]
                val optionData = optionDataArray[i]

                if (option.type != optionData.type) {
                    return false
                }
                if (option.name != optionData.name) {
                    return false
                }
            } catch (e: ArrayIndexOutOfBoundsException) {
                return false
            }
        }
        return true
    }
}