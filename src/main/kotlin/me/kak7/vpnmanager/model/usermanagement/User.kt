package me.kak7.vpnmanager.model.usermanagement

import javax.persistence.Entity

// todo
@Entity
class User(
    val id: Int,
    val role: UserRole
)
