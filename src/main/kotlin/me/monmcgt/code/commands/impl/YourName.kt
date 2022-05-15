package me.monmcgt.code.commands.impl

import me.monmcgt.code.commands.CommandAbstract
import me.monmcgt.code.commands.CommandInfo
import me.monmcgt.code.commands.RegisterCommand
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.OptionData

@RegisterCommand
@CommandInfo(name = "yourname", description = "Returns your name")
class YourName : CommandAbstract() {
    override val optionDataArray: Array<OptionData>
        get() = arrayOf(OptionData(OptionType.STRING, "name", "Your name", true))

    override fun onSlashCommandInteraction() {
        event.getOption("name")?.let {
            sendEmphemralMessage("Your name is ${it.asString}")
        }
    }
}