package com.kubista.userexplorer.ui.detail

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kubista.userexplorer.R
import com.kubista.userexplorer.databinding.ActivityUserDetailBinding
import com.kubista.userexplorer.model.User
import com.kubista.userexplorer.model.UserType
import com.kubista.userexplorer.utils.*

class UserDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailBinding
    private lateinit var viewModel: UserDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail)

        viewModel = ViewModelProviders.of(this).get(UserDetailViewModel::class.java)

        val user = object : User() {
            override fun getName(): String {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun getAvatarUrl(): String {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun getType(): UserType {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }

        viewModel.bind(user)
        binding.viewModel = viewModel
    }
}