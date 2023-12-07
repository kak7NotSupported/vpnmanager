package me.kak7.vpnmanager.model

import me.kak7.vpnmanager.model.usermanagement.User
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration

object HibernateUtil {
    
    private val sessionFactory: SessionFactory = buildSessionFactory()

    private fun buildSessionFactory(): SessionFactory {
        try {
            return Configuration().buildSessionFactory()
        } catch (ex: Throwable) {
            System.err.println("Initial SessionFactory creation failed." + ex)
            throw ExceptionInInitializerError(ex)
        }
    }

    fun <T> performOperation(operation: (session: org.hibernate.Session) -> T): T {
        val session = sessionFactory.openSession()
        val transaction = session.beginTransaction()

        return try {
            val result = operation(session)
            transaction.commit()
            result
        } catch (ex: Exception) {
            transaction.rollback()
            throw ex
        } finally {
            session.close()
        }
    }
}

