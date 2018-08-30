package com.kubista.userexplorer.ui.detail

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kubista.userexplorer.R
import com.kubista.userexplorer.databinding.ActivityRepoDetailBinding
import com.kubista.userexplorer.model.IRepo
import com.kubista.userexplorer.utils.*

class RepoDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepoDetailBinding
    private lateinit var viewModel: RepoDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_repo_detail)

        viewModel = ViewModelProviders.of(this).get(RepoDetailViewModel::class.java)

        val repo = object : IRepo {
            override fun isBitbucektRepo(): Boolean {
                return intent.getBooleanExtra(KEY_REPO_TYPE, false)
            }

            override fun getOwnerName(): String {
                return intent.getStringExtra(KEY_OWNER_NAME)
            }

            override fun getOwnerAvatarUrl(): String {
                return intent.getStringExtra(KEY_OWNER_AVATAR_URL)
            }

            override fun getRepositoryTitle(): String {
                return intent.getStringExtra(KEY_REPO_TITLE)
            }

            override fun getRepositoryDescription(): String {
                return intent.getStringExtra(KEY_REPO_DESC)
            }
        }

        viewModel.bind(repo)
        binding.viewModel = viewModel
    }
}