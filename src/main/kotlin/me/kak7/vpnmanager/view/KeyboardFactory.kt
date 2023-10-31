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
            KeyboardType.CONTACT_US -> getContactUsInlineKeyboard()
            KeyboardType.REFERRAL -> getReferralInlineKeyboard()
            KeyboardType.MY_ACCOUNT -> getMyAccountInlineKeyboard()
            KeyboardType.EMPTY -> getEmptyKeyboard()
            KeyboardType.PLATFORMS -> getPlatformsInlineKeyboard()
            // todo about button
            KeyboardType.ABOUT_US -> getReferralInlineKeyboard()
        }
    }

    private fun getContactUsInlineKeyboard(): InlineKeyboardMarkup {

        val markup = InlineKeyboardMarkup()

        val row1 = mutableListOf<InlineKeyboardButton>()

        InlineKeyboardButton().let {
            it.text = LocaleManager.getMessage("contact_us_button")
            it.url = "https://t.me/Nick_s3ller"

            it.callbackData = "referal button"
            row1.add(it)
        }

        val row2 = mutableListOf<InlineKeyboardButton>()

        InlineKeyboardButton().let {
            it.text = LocaleManager.getMessage("cancel")
            it.callbackData = "referal button"
//            it.webApp.url = "/"
            row2.add(it)
        }

        markup.keyboard = listOf(row1, row2)
        return markup
    }

    private fun getPlatformsInlineKeyboard(): InlineKeyboardMarkup {

        val markup = InlineKeyboardMarkup()

        val row1 = mutableListOf<InlineKeyboardButton>()

        InlineKeyboardButton().let {
            it.text = LocaleManager.getMessage("android")

            it.callbackData = "referal button"
            row1.add(it)
        }

        InlineKeyboardButton().let {
            it.text = LocaleManager.getMessage("apple")

            it.callbackData = "referal button"
            row1.add(it)
        }

        val row2 = mutableListOf<InlineKeyboardButton>()

        InlineKeyboardButton().let {
            it.text = LocaleManager.getMessage("windows")
            it.callbackData = "referal button"
            row2.add(it)
        }

        InlineKeyboardButton().let {
            it.text = LocaleManager.getMessage("other")
            it.callbackData = "referal button"
            row2.add(it)
        }

        markup.keyboard = listOf(row1, row2)
        return markup
    }

    private fun getMyAccountInlineKeyboard(): InlineKeyboardMarkup {

        val markup = InlineKeyboardMarkup()
        val row1 = mutableListOf<InlineKeyboardButton>()

        InlineKeyboardButton().let {
            it.text = LocaleManager.getMessage("refresh")
            it.callbackData = "referal button"
            row1.add(it)
        }

        val row2 = mutableListOf<InlineKeyboardButton>()

        InlineKeyboardButton().let {
            it.text = LocaleManager.getMessage("cancel")
            it.callbackData = "referal button"
            row2.add(it)
        }

        markup.keyboard = listOf(row1, row2)
        return markup
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
        keyboardMarkup.resizeKeyboard = true
        // Создаем строки клавиатуры
        val row1 = KeyboardRow()
        row1.add(KeyboardButton(LocaleManager.getMessage("account_button")))
        row1.add(KeyboardButton(LocaleManager.getMessage("platforms")))

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
        EMPTY,
        MY_ACCOUNT,
        PLATFORMS,
        ABOUT_US
    }

}