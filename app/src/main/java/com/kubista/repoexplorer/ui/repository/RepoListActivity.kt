package com.kubista.repoexplorer.ui.repository

import com.kubista.repoexplorer.base.BaseViewModel
import com.kubista.repoexplorer.network.GitHubApi
import javax.inject.Inject

/**
 * Created by alek on 08/08/2018.
 */
class RepoListActivity : BaseViewModel() {
    @Inject
    lateinit var postApi: GitHubApi
}