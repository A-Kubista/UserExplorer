package com.kubista.userexplorer.model

import android.os.Parcelable


abstract class User {
    abstract fun getName(): String
    abstract fun getAvatarUrl(): String
    abstract fun getType(): UserType
    abstract fun getParcelable() : Parcelable
}
