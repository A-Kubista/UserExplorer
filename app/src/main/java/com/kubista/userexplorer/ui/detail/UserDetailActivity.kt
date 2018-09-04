package com.kubista.userexplorer.ui.detail

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import com.kubista.userexplorer.R
import com.kubista.userexplorer.base.viewModelFactory
import com.kubista.userexplorer.databinding.ActivityUserDetailBinding
import com.kubista.userexplorer.model.user.User
import com.kubista.userexplorer.utils.KEY_USER_PARCEL

class UserDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailBinding
    private lateinit var viewModel: UserDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail)

        var userParcel = intent.getParcelableExtra<Parcelable>(KEY_USER_PARCEL)
        if (userParcel == null && savedInstanceState != null) {
            userParcel = savedInstanceState.getParcelable(KEY_USER_PARCEL)
        }

        val user = if (userParcel != null) userParcel as User else null

        viewModel = ViewModelProviders.of(
                this,
                viewModelFactory { UserDetailViewModel(user) }
        ).get(UserDetailViewModel::class.java)

        binding.viewModel = viewModel
    }

    public override fun onSaveInstanceState(bundle: Bundle) {
        super.onSaveInstanceState(bundle)
        viewModel.saveInstance(bundle)
    }
}