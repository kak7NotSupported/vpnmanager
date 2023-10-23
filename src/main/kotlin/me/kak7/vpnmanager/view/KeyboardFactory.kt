package me.kak7.vpnmanager.view

import me.kak7.vpnmanager.model.localization.LocaleManager
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow


object KeyboardFactory {

    fun getKeyboard(type: KeyboardType): ReplyKeyboard {
        return when (type) {
            KeyboardType.START -> getStartKeyboard()
            KeyboardType.CONTACT_US -> getContactUsKeyboard()
            KeyboardType.REFERRAL -> getReferralInlineKeyboard()
            KeyboardType.EMPTY -> getEmptyKeyboard()
        }
    }

    // В KeyboardFactory
    private fun getReferralInlineKeyboard(): InlineKeyboardMarkup {

        val markup = InlineKeyboardMarkup()
        val inlineKeyboardButton = InlineKeyboardButton()
        inlineKeyboardButton.text = LocaleManager.getMessage("cancel")
        inlineKeyboardButton.callbackData = "referal button"

        val row = mutableListOf<InlineKeyboardButton>()
        row.add(inlineKeyboardButton)
        markup.keyboard = listOf(row)

        return markup
    }


    private fun getStartKeyboard(): ReplyKeyboard {
        val keyboardMarkup = ReplyKeyboardMarkup()
        // Создаем строки клавиатуры
        val row1 = KeyboardRow()
        row1.add(KeyboardButton(LocaleManager.getMessage("my_accounts_button")))
        row1.add(KeyboardButton(LocaleManager.getMessage("add_account_button")))

        val row2 = KeyboardRow()
        row2.add(KeyboardButton(LocaleManager.getMessage("about_us_button")))

        // Добавляем строки в клавиатуру
        val keyboard: MutableList<KeyboardRow> = ArrayList()
        keyboard.add(row1)
        keyboard.add(row2)

        keyboardMarkup.keyboard = keyboard
        return keyboardMarkup
    }

    private fun getEmptyKeyboard(): ReplyKeyboardRemove {
        return ReplyKeyboardRemove(true)
    }

    private fun getContactUsKeyboard(): ReplyKeyboardMarkup {
        return ReplyKeyboardMarkup()
    }

    enum class KeyboardType {
        START,
        CONTACT_US,
        REFERRAL,
        EMPTY
    }

}