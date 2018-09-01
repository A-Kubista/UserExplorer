package com.kubista.userexplorer.ui.user

import android.arch.lifecycle.MutableLiveData
import com.kubista.userexplorer.base.BaseViewModel
import com.kubista.userexplorer.model.User
import com.kubista.userexplorer.model.UserType

class UserViewModel : BaseViewModel() {
    private val name = MutableLiveData<String>()
    private val avatarUrl = MutableLiveData<String>()
    private val type = MutableLiveData<UserType>()

    fun bind(user: User) {
        name.value = user.getName()
        avatarUrl.value = user.getAvatarUrl()
        type.value = user.getType()
    }

    fun getAvatarUrl(): MutableLiveData<String> {
        return avatarUrl
    }

    fun getName(): MutableLiveData<String> {
        return name
    }

    fun getBitBucketLogoVisibility(): MutableLiveData<UserType> {
        return type
    }
}