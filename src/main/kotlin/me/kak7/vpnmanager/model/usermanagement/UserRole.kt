package me.kak7.vpnmanager.model.usermanagement

enum class UserRole(val permissions: Permissions) {
    ADMIN(Permissions(true)),
    CLIENT(Permissions(false)),
    DEFAULT(Permissions(false))
}