package com.kubista.repoexplorer.ui.repository

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kubista.repoexplorer.R
import com.kubista.repoexplorer.databinding.ItemRepositoryBinding
import com.kubista.repoexplorer.model.GitHubRepo

/**
 * Created by alek on 09/08/2018.
 */

class RepositoryListAdapter: RecyclerView.Adapter<RepositoryListAdapter.ViewHolder>() {
    private lateinit var repoList:List<GitHubRepo>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryListAdapter.ViewHolder {
        val binding: ItemRepositoryBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_repository, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryListAdapter.ViewHolder, position: Int) {
        holder.bind(repoList[position])
    }

    override fun getItemCount(): Int {
        return if(this::repoList.isInitialized) repoList.size else 0
    }

    fun updatePostList(postList:List<GitHubRepo>){
        this.repoList = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemRepositoryBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = RepoViewModel()

        fun bind(post:GitHubRepo){
            viewModel.bind(post)
            binding.viewModel = viewModel
        }
    }
}