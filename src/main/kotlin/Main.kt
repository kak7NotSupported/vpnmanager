import me.kak7.vpnmanager.controller.TelegramBot
import me.kak7.vpnmanager.model.HibernateUtil
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

fun main() {
    HibernateUtil

    try {
        TelegramBotsApi(DefaultBotSession::class.java).registerBot(TelegramBot())
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
