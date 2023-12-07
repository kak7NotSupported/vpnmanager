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

    enum class KeyboardType {
        START,
        CONTACT_US,
        REFERRAL,
        EMPTY,
        MY_ACCOUNT,
        PLATFORMS,
        ABOUT_US
    }

    fun getKeyboard(type: KeyboardType): ReplyKeyboard {
        return when (type) {
            KeyboardType.START -> getStartKeyboard()
            KeyboardType.CONTACT_US -> getContactUsInlineKeyboard()
            KeyboardType.REFERRAL -> getReferralInlineKeyboard()
            KeyboardType.MY_ACCOUNT -> getMyAccountInlineKeyboard()
            KeyboardType.EMPTY -> getEmptyKeyboard()
            KeyboardType.PLATFORMS -> getPlatformsInlineKeyboard()
            KeyboardType.ABOUT_US -> getReferralInlineKeyboard() // Заметьте, здесь используется тот же метод, что и для REFERRAL
        }
    }

    private fun getStartKeyboard(): ReplyKeyboard {
        val keyboardMarkup = ReplyKeyboardMarkup()
        keyboardMarkup.resizeKeyboard = true

        val row1 = KeyboardRow().apply {
            add(KeyboardButton(LocaleManager.getMessage("account_button")))
            add(KeyboardButton(LocaleManager.getMessage("platforms")))
        }

        val row2 = KeyboardRow().apply {
            add(KeyboardButton(LocaleManager.getMessage("about_us_button")))
        }

        return keyboardMarkup.apply {
            keyboard = listOf(row1, row2)
        }
    }

    private fun getContactUsInlineKeyboard(): InlineKeyboardMarkup {
        return InlineKeyboardMarkup().apply {
            keyboard = listOf(
                listOf(createInlineKeyboardButton(LocaleManager.getMessage("contact_us_button"), "contact_us")),
                listOf(createInlineKeyboardButton(LocaleManager.getMessage("cancel"), "cancel"))
            )
        }
    }

    private fun getReferralInlineKeyboard(): InlineKeyboardMarkup {
        return InlineKeyboardMarkup().apply {
            keyboard = listOf(
                listOf(createInlineKeyboardButton(LocaleManager.getMessage("cancel"), "cancel"))
            )
        }
    }

    private fun getMyAccountInlineKeyboard(): InlineKeyboardMarkup {
        // todo реализовать кнопки аккаунта через hybernate
        return InlineKeyboardMarkup().apply {
            keyboard = listOf(
                listOf(createInlineKeyboardButton(LocaleManager.getMessage("refresh"), "refresh"), createInlineKeyboardButton(LocaleManager.getMessage("refresh"), "refresh")),
                listOf(createInlineKeyboardButton(LocaleManager.getMessage("cancel"), "cancel"))
            )
        }
    }

    private fun getPlatformsInlineKeyboard(): InlineKeyboardMarkup {
        return InlineKeyboardMarkup().apply {
            keyboard = listOf(
                listOf(createInlineKeyboardButton(LocaleManager.getMessage("android"), "android"),
                    createInlineKeyboardButton(LocaleManager.getMessage("apple"), "apple")),
                listOf(createInlineKeyboardButton(LocaleManager.getMessage("windows"), "windows"),
                    createInlineKeyboardButton(LocaleManager.getMessage("other"), "other"))
            )
        }
    }

    private fun getEmptyKeyboard(): ReplyKeyboardRemove {
        return ReplyKeyboardRemove(true)
    }

    private fun createInlineKeyboardButton(text: String, callbackData: String): InlineKeyboardButton {
        return InlineKeyboardButton().apply {
            this.text = text
            this.callbackData = callbackData
        }
    }

}
