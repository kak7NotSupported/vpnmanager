package me.kak7.vpnmanager.model.config

import me.kak7.vpnmanager.model.YamlManager

object ConfigManager : YamlManager("config.yaml") {
    fun getToken(): String {
        return rootNode.node("bot", "token").string ?: ""
    }
}