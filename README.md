# Kotlin JDA Base

[//]: # (<hr>)

A JDA Starter Project in Kotlin with a few features.

## Important Classes / Packages

[//]: # (<hr>)

<ul>
<li>
<a href="https://github.com/Monmcgt/Kotlin-JDA-Base/blob/master/src/main/kotlin/me/monmcgt/code/Main.kt">Main Class</a>
</li>
<li>
<a href="https://github.com/Monmcgt/Kotlin-JDA-Base/tree/master/src/main/kotlin/me/monmcgt/code/commands/impl">Commands</a>
</li>
</ul>

## How to use

[//]: # (<hr>)

<ul>
<li>
Create a class that extends <a href="https://github.com/Monmcgt/Kotlin-JDA-Base/blob/master/src/main/kotlin/me/monmcgt/code/commands/CommandAbstract.kt">CommandAbstract</a> and put <a href="https://github.com/Monmcgt/Kotlin-JDA-Base/blob/master/src/main/kotlin/me/monmcgt/code/commands/CommandInfo.kt">CommandInfo</a> annotation on it.
</li>
<li>
After that, to register your command, put <a href="https://github.com/Monmcgt/Kotlin-JDA-Base/blob/master/src/main/kotlin/me/monmcgt/code/commands/RegisterCommand.kt">RegisterCommand</a> annotation OR change your autoRegisterClass in <a href="https://github.com/Monmcgt/Kotlin-JDA-Base/blob/master/src/main/kotlin/me/monmcgt/code/commands/CommandInfo.kt">CommandInfo</a> annotation to true (default is false).
</li>
</ul>

### Example
#### without command options (<a href="https://github.com/Monmcgt/Kotlin-JDA-Base/blob/master/src/main/kotlin/me/monmcgt/code/commands/impl/Ping.kt">Ping.kt</a>)
```kotlin
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
```
<span><br></span>
#### without command options (<a href="https://github.com/Monmcgt/Kotlin-JDA-Base/blob/master/src/main/kotlin/me/monmcgt/code/commands/impl/UserAvatar.kt">UserAvatar.kt</a>)
```kotlin
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
```
<span><br></span>
#### with command options (<a href="https://github.com/Monmcgt/Kotlin-JDA-Base/blob/master/src/main/kotlin/me/monmcgt/code/commands/impl/YourName.kt">YourName.kt</a>)
```kotlin
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
```

## Library

[//]: # (<hr>)

<ul>
<li>
<a href="https://github.com/DV8FromTheWorld/JDA">JDA (Java Discord API)</a>
</li>
<li>
<a href="https://github.com/google/gson">Gson</a>
</li>
</ul>

## Inspiration

[//]: # (<hr>)

<ul>
<li>
<strong><a href="https://spring.io">Spring Framework</a></strong> - Commands registering system (<a href="https://www.oracle.com/technical-resources/articles/java/javareflection.html">Java Reflection</a>)
</li>
</ul>
