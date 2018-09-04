package com.kubista.userexplorer.ui.user

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kubista.userexplorer.R
import com.kubista.userexplorer.base.viewModelFactory
import com.kubista.userexplorer.databinding.ActivityUserListBinding
import com.kubista.userexplorer.injection.component.DaggerViewInjector
import com.kubista.userexplorer.injection.module.AppModule
import com.kubista.userexplorer.injection.module.DatabaseModule
import com.kubista.userexplorer.model.user.UserDao
import com.kubista.userexplorer.model.user.UserRepository
import javax.inject.Inject


class UserListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserListBinding
    private lateinit var viewModel: UserListViewModel

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_list)

        DaggerViewInjector
                .builder()
                .appModule(AppModule(application))
                .databaseModule(DatabaseModule(application))
                .build()
                .inject(this)

        viewModel = ViewModelProviders.of(
                this,
                viewModelFactory { UserListViewModel(savedInstanceState, userRepository, userDao) }
        ).get(UserListViewModel::class.java)


        binding.viewModel = viewModel
    }

    public override fun onSaveInstanceState(bundle: Bundle) {
        super.onSaveInstanceState(bundle)
        viewModel.saveInstance(bundle)
    }
}