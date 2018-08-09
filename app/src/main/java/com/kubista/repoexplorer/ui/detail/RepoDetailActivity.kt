package com.kubista.repoexplorer.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.kubista.repoexplorer.R
import com.kubista.repoexplorer.databinding.ActivityRepoDetailBinding
import com.kubista.repoexplorer.model.IRepo
import com.kubista.repoexplorer.ui.repo.RepoViewModel
import com.kubista.repoexplorer.utils.KEY_OWNER_AVATAR_URL
import com.kubista.repoexplorer.utils.KEY_OWNER_NAME
import com.kubista.repoexplorer.utils.KEY_REPO_DESC
import com.kubista.repoexplorer.utils.KEY_REPO_TITLE

class RepoDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepoDetailBinding
    private lateinit var viewModel: RepoDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_repo_detail)

        viewModel = ViewModelProviders.of(this).get(RepoDetailViewModel::class.java)

        val repo = object: IRepo {
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