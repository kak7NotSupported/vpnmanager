import me.kak7.vpnmanager.controller.TelegramBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

fun main() {
    try {
        TelegramBotsApi(DefaultBotSession::class.java).registerBot(TelegramBot())
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
