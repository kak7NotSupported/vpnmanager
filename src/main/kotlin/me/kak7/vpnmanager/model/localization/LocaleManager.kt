package me.kak7.vpnmanager.model.localization

import me.kak7.vpnmanager.model.YamlManager

object LocaleManager : YamlManager("localization/locale_ru.yaml") {

    fun getMessage(message: String, vararg args: String? = arrayOf()): String {
        val template = rootNode.node("messages", message).string ?: message
        var argIndex = 0

        return template.replace(Regex("%s")) {
            args.getOrNull(argIndex++) ?: "%s"
        }
    }
}