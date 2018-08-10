package com.kubista.repoexplorer.ui.repo

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kubista.repoexplorer.R
import com.kubista.repoexplorer.databinding.ItemRepoBinding
import com.kubista.repoexplorer.model.IRepo
import com.kubista.repoexplorer.ui.detail.RepoDetailActivity
import com.kubista.repoexplorer.utils.*
import java.util.*


class RepoListAdapter : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {
    private lateinit var repoList: List<IRepo>
    private lateinit var repoListRaw: List<IRepo>
    private lateinit var contex: Context
    private var sortEnabled: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListAdapter.ViewHolder {
        contex = parent.context
        val binding: ItemRepoBinding = DataBindingUtil.inflate(LayoutInflater.from(contex), R.layout.item_repo, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoListAdapter.ViewHolder, position: Int) {
        holder.bind(repoList[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(contex, RepoDetailActivity::class.java)
            intent.putExtra(KEY_OWNER_NAME, repoList[position].getOwnerName())
            intent.putExtra(KEY_OWNER_AVATAR_URL, repoList[position].getOwnerAvatarUrl())
            intent.putExtra(KEY_REPO_TITLE, repoList[position].getRepositoryTitle())
            intent.putExtra(KEY_REPO_DESC, repoList[position].getRepositoryDescription())
            intent.putExtra(KEY_REPO_TYPE, repoList[position].isBitbucektRepo())
            startActivity(contex, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return if (::repoList.isInitialized) repoList.size else 0
    }

    fun updateRepoList(repoList: List<IRepo>) {
        this.repoList = repoList
        this.repoListRaw = repoList.toList()

        if (sortEnabled) {
            sortRepoList()
        }

        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = RepoViewModel()

        fun bind(repo: IRepo) {
            viewModel.bind(repo)
            binding.viewModel = viewModel
        }
    }

    fun toggleSort(sortEnabled: Boolean) {
        this.sortEnabled = sortEnabled

        if (sortEnabled) {
            sortRepoList()
        }else{
            repoList = repoListRaw.toList()
        }

        notifyDataSetChanged()
    }

    private fun sortRepoList(){
        Collections.sort(repoList) { lhs, rhs ->
            when {
                lhs.getRepositoryTitle().capitalize() > rhs.getRepositoryTitle().capitalize() -> 1
                lhs.getRepositoryTitle().capitalize() < rhs.getRepositoryTitle().capitalize() -> -1
                else -> 0
            }
        }
    }

}