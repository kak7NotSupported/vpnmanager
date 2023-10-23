package me.kak7.vpnmanager.controller

import me.kak7.vpnmanager.model.config.ConfigManager
import me.kak7.vpnmanager.model.localization.LocaleManager
import me.kak7.vpnmanager.view.KeyboardFactory
import me.kak7.vpnmanager.view.MessageBuilder
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import java.lang.Exception

class TelegramBot : TelegramLongPollingBot() {
    override fun getBotUsername() = "main"
    override fun getBotToken() = ConfigManager.getToken()

    override fun onUpdateReceived(update: Update?) {


        update?.takeIf { it.hasMessage() && it.message.hasText() }?.let {
            // todo update.message.from.languageCode
            val messageText = it.message.text
            val chatId = it.message.chatId.toString()
            println(messageText)
            val sendMessage = when (messageText) {
                "/start", "/get_menu" -> MessageBuilder.buildMessage(chatId, "welcome", KeyboardFactory.KeyboardType.START)
                "/contact_us" -> MessageBuilder.buildMessage(chatId, "contact_us")
                "/referral" -> MessageBuilder.buildMessage(chatId, "referral", KeyboardFactory.KeyboardType.REFERRAL)
                "/localereload" -> {
                    LocaleManager.reloadData()
                    null
                }
                else -> MessageBuilder.buildMessage(chatId, "unknown_command", KeyboardFactory.KeyboardType.START)
            }

            try {
                sendMessage?.let { execute(sendMessage) }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
