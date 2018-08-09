package com.kubista.repoexplorer.ui.repo

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kubista.repoexplorer.R
import com.kubista.repoexplorer.databinding.ItemRepoBinding
import com.kubista.repoexplorer.model.GitHubRepo

class RepoListAdapter: RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {
    private lateinit var repoList:List<GitHubRepo>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListAdapter.ViewHolder {
        val binding: ItemRepoBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_repo, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoListAdapter.ViewHolder, position: Int) {
        holder.bind(repoList[position])
    }

    override fun getItemCount(): Int {
        return if(::repoList.isInitialized) repoList.size else 0
    }

    fun updateRepoList(repoList:List<GitHubRepo>){
        this.repoList = repoList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemRepoBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = RepoViewModel()

        fun bind(repo: GitHubRepo){
            viewModel.bind(repo)
            binding.viewModel = viewModel
        }
    }
}