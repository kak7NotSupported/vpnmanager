package me.kak7.vpnmanager.model.localization

import me.kak7.vpnmanager.model.YamlManager

object LocaleManager : YamlManager("localization/locale_ru.yaml") {

    fun getMessage(message: String): String {
        return rootNode.node("messages", message).string ?: "None"
    }
}