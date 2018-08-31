package com.kubista.userexplorer.ui.detail

import android.view.View
import com.kubista.userexplorer.BaseViewModel
import com.kubista.userexplorer.model.User
import com.kubista.userexplorer.model.UserType

class UserDetailViewModel : BaseViewModel() {
    private lateinit var name: String
    private lateinit var avatarUrl: String
    private lateinit var type: UserType

    fun bind(user: User) {
        name = user.getName()
        avatarUrl = user.getAvatarUrl()
        type = user.getType()
    }

    fun getAvatarUrl(): String {
        return avatarUrl
    }

    fun getUserName(): String {
        return name
    }

    fun getBitBucketLogoVisibility(): Int {
        return View.VISIBLE
    }
}