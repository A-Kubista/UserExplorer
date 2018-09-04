package com.kubista.userexplorer.ui.detail

import android.os.Bundle
import android.os.Parcelable
import com.kubista.userexplorer.R
import com.kubista.userexplorer.base.BaseViewModel
import com.kubista.userexplorer.model.user.User
import com.kubista.userexplorer.model.user.UserRepository
import com.kubista.userexplorer.model.user.UserType
import com.kubista.userexplorer.utils.KEY_SAVED_USER_ID
import com.kubista.userexplorer.utils.KEY_SAVED_USER_TYPE
import com.kubista.userexplorer.utils.NO_INT_VAL
import java.io.Serializable

class UserDetailViewModel(
        selectedUserParcel: Parcelable?,
        cachedUserId: Int,
        cachedUserType: Serializable?,
        userRepository: UserRepository
) : BaseViewModel() {

    private var user: User? = null

    override fun inject() {
        injector.inject(this)
    }

    fun saveInstance(bundle: Bundle) {
        if (user != null) {
            bundle.putInt(KEY_SAVED_USER_ID, user!!.getUniqueId())
            bundle.putSerializable(KEY_SAVED_USER_TYPE, user!!.getType())
        }
    }

    init {
        if (selectedUserParcel != null) {
            user = selectedUserParcel as User
        } else if (cachedUserId != NO_INT_VAL && cachedUserType != null) {
            user = if (cachedUserType as UserType == UserType.DAILYMOTION) {
                userRepository.getDailyUsermotionById(cachedUserId)
            } else {
                userRepository.getGithubUsermotionById(cachedUserId)
            }
        }
    }


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