package com.kubista.userexplorer.ui.detail

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import com.kubista.userexplorer.R
import com.kubista.userexplorer.base.viewModelFactory
import com.kubista.userexplorer.databinding.ActivityUserDetailBinding
import com.kubista.userexplorer.injection.component.DaggerViewInjector
import com.kubista.userexplorer.injection.module.AppModule
import com.kubista.userexplorer.injection.module.DatabaseModule
import com.kubista.userexplorer.model.user.UserRepository
import com.kubista.userexplorer.utils.KEY_SAVED_USER_ID
import com.kubista.userexplorer.utils.KEY_SAVED_USER_TYPE
import com.kubista.userexplorer.utils.KEY_USER_PARCEL
import com.kubista.userexplorer.utils.NO_INT_VAL
import javax.inject.Inject

class UserDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailBinding
    private lateinit var viewModel: UserDetailViewModel

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerViewInjector
                .builder()
                .appModule(AppModule(application))
                .databaseModule(DatabaseModule(application))
                .build()
                .inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail)

        var userParcel = intent.getParcelableExtra<Parcelable>(KEY_USER_PARCEL)
        val cachedUserId = savedInstanceState?.getInt(KEY_SAVED_USER_ID, NO_INT_VAL) ?: NO_INT_VAL
        val cachedUserType = savedInstanceState?.getSerializable(KEY_SAVED_USER_TYPE)

        viewModel = ViewModelProviders.of(
                this,
                viewModelFactory { UserDetailViewModel(userParcel, cachedUserId, cachedUserType, userRepository) }
        ).get(UserDetailViewModel::class.java)

        binding.viewModel = viewModel
    }

    public override fun onSaveInstanceState(bundle: Bundle) {
        super.onSaveInstanceState(bundle)
        viewModel.saveInstance(bundle)
    }
}