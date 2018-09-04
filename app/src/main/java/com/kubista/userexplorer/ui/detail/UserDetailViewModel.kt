package com.kubista.userexplorer.ui.detail

import android.os.Bundle
import com.kubista.userexplorer.R
import com.kubista.userexplorer.base.BaseViewModel
import com.kubista.userexplorer.model.user.User
import com.kubista.userexplorer.model.user.UserType
import com.kubista.userexplorer.utils.KEY_USER_PARCEL

class UserDetailViewModel(cachedUser: User?) : BaseViewModel() {
    override fun inject() {
        injector.inject(this)
    }

    /**
     *  normally you should not save complex object to bundle,
     *  but rather id of an object to retrieve it again from storage,
     *  however user object contains only 3 fields
     *  */
    fun saveInstance(bundle: Bundle) {
        if (user != null) {
            bundle.putParcelable(KEY_USER_PARCEL, user.getParcelable())
        }
    }

    private val user: User? = cachedUser

    fun getAvatarUrl(): String {
        return user?.getAvatarUrl() ?: ""
    }

    fun getUserName(): String {
        return user?.getName() ?: ""
    }

    fun getImageTypeResource(): Int {
        return if (user?.getType() == UserType.GITHUB) R.drawable.logo_github else R.drawable.logo_dailymotion
    }
}