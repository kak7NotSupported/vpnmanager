package me.kak7.vpnmanager.view

import me.kak7.vpnmanager.model.localization.LocaleManager
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText

object MessageBuilder {

    fun buildMessage(
        chatId: String,
        textKey: String,
        keyboardType: KeyboardFactory.KeyboardType? = null,
        vararg args: String
    ): SendMessage {
        return SendMessage().apply {
            this.chatId = chatId
            this.text = LocaleManager.getMessage(textKey, *args)
            this.replyMarkup = keyboardType?.let { KeyboardFactory.getKeyboard(it) }

        }
    }

//    fun editMessageText() : EditMessageText {
//
//    }
}
