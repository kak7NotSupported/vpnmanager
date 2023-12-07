package me.kak7.vpnmanager.model.usermanagement

import javax.persistence.Entity
import javax.persistence.Table
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "user", schema = "public")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Int,

    @Column(name = "username")
    val username: String,

    @Column(name = "balance")
    val balance: Double = 0.0,

    @Column(name = "role_id")
    val roleId: Int? = null,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "lang_code")
    val langCode: String? = null
    
)

