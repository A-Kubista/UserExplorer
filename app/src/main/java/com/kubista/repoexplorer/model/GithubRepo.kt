package com.kubista.repoexplorer.model

/**
 * Created by alek on 08/08/2018.
 */

/**
 * Class which provides a model for GitHub repository
 * @constructor Sets all properties of the repository
 * @property id the unique identifier of the repository
 * @property name the title of the repository
 * @property description the content of the repository
 */
data class GithubRepo( val id: Int, val name: String, val description: String)