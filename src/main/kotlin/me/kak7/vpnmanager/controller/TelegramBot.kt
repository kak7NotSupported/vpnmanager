package me.kak7.vpnmanager.controller

import me.kak7.vpnmanager.model.config.ConfigManager
import me.kak7.vpnmanager.model.localization.LocaleManager
import me.kak7.vpnmanager.view.KeyboardFactory
import me.kak7.vpnmanager.view.MessageBuilder
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update

class TelegramBot : TelegramLongPollingBot() {
    override fun getBotUsername() = "main"
    override fun getBotToken() = ConfigManager.getToken()

    override fun onUpdateReceived(update: Update?) {


        update?.takeIf { it.hasMessage() && it.message.hasText() }?.let {
            // todo update.message.from.languageCode

            // todo проверям сообщение, проверяем права, дальше логика
            //

            val messageText = it.message.text
            val chatId = it.message.chatId.toString()
            println(messageText)
            val sendMessage = when (messageText) {
                "/start", "/get_menu" -> MessageBuilder.buildMessage(
                    chatId,
                    "welcome",
                    KeyboardFactory.KeyboardType.START
                )

                "/contact_us" -> MessageBuilder.buildMessage(
                    chatId,
                    "contact_us",
                    KeyboardFactory.KeyboardType.CONTACT_US,
                    update.message.from.id.toString()
                )

                "/referral" -> MessageBuilder.buildMessage(
                    chatId,
                    "referral_text",
                    KeyboardFactory.KeyboardType.REFERRAL
                )

                "/localereload" -> {
                    LocaleManager.reloadData()
                    null
                }

                "/admincommands" -> MessageBuilder.buildMessage(chatId, "admincommands")
                LocaleManager.getMessage("platforms") -> MessageBuilder.buildMessage(
                    chatId,
                    "platforms_text",
                    KeyboardFactory.KeyboardType.PLATFORMS
                )

                LocaleManager.getMessage("about_us_button") -> MessageBuilder.buildMessage(
                    chatId,
                    "about_us_text",
                    KeyboardFactory.KeyboardType.ABOUT_US
                )
                // todo reset password
                LocaleManager.getMessage("account_button") -> MessageBuilder.buildMessage(chatId, "account_text")
                else -> MessageBuilder.buildMessage(chatId, "unknown_command", KeyboardFactory.KeyboardType.START)
            }

            try {
                sendMessage?.let {
                    it.enableMarkdown(true)
                    // todo проверка на markdown
                    execute(sendMessage)
                }

            } catch (e: Exception) {
                // todo send error message
                e.printStackTrace()
            }
        }
    }
}
