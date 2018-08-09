package com.kubista.repoexplorer.ui.repository

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.kubista.repoexplorer.R
import com.kubista.repoexplorer.databinding.ActivityRepositoryListBinding

/**
 * Created by alek on 09/08/2018.
 */


class RepoListActivity: AppCompatActivity() {
    private lateinit var binding: ActivityRepositoryListBinding
    private lateinit var viewModel: RepoListViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_repository_list)
        binding.postList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this).get(RepoListViewModel::class.java)
        binding.viewModel = viewModel
    }
}