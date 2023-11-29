package me.kak7.vpnmanager.model

import org.spongepowered.configurate.ConfigurationNode
import org.spongepowered.configurate.yaml.NodeStyle
import org.spongepowered.configurate.yaml.YamlConfigurationLoader

abstract class YamlManager(private val filePath: String) {

    lateinit var rootNode: ConfigurationNode
    init {
        loadData()
    }

    private fun loadData() {
        val loader = YamlConfigurationLoader.builder()
            .path(java.nio.file.Paths.get("src/main/resources/$filePath"))
            .nodeStyle(NodeStyle.BLOCK)
            .build()

        rootNode = loader.load()
    }

    public fun reloadData() {
        loadData()
    }
}