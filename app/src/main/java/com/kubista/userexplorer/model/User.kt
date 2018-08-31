package com.kubista.userexplorer.model

abstract class User {
    abstract fun getName(): String
    abstract fun getAvatarUrl(): String
    abstract fun getType(): UserType
}
