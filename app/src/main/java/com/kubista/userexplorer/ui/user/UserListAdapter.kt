package com.kubista.userexplorer.ui.user

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kubista.userexplorer.R
import com.kubista.userexplorer.databinding.ItemUserBinding
import com.kubista.userexplorer.model.User
import com.kubista.userexplorer.ui.detail.UserDetailActivity
import com.kubista.userexplorer.utils.*
import java.util.*


class UserListAdapter : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    private lateinit var userList: List<User>
    private lateinit var userListRaw: List<User>
    private lateinit var contex: Context
    private var sortEnabled: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdapter.ViewHolder {
        contex = parent.context
        val binding: ItemUserBinding = DataBindingUtil.inflate(LayoutInflater.from(contex), R.layout.item_user, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListAdapter.ViewHolder, position: Int) {
        holder.bind(userList[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(contex, UserDetailActivity::class.java)
            intent.putExtra(KEY_USER_PARCEL,userList[position].getParcelable())
            startActivity(contex, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return if (::userList.isInitialized) userList.size else 0
    }

    fun updateUserList(userList: List<User>) {
        this.userList = userList
        this.userListRaw = userList.toList()

        if (sortEnabled) {
            sortUserList()
        }

        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = UserViewModel()

        fun bind(user: User) {
            viewModel.bind(user)
            binding.viewModel = viewModel
        }
    }

    fun toggleSort(sortEnabled: Boolean) {
        this.sortEnabled = sortEnabled

        if (sortEnabled) {
            sortUserList()
        } else {
            userList = userListRaw.toList()
        }

        notifyDataSetChanged()
    }

    private fun sortUserList() {
        Collections.sort(userList) { lhs, rhs ->
            when {
                lhs.getName().capitalize() > rhs.getName().capitalize() -> 1
                lhs.getName().capitalize() < rhs.getName().capitalize() -> -1
                else -> 0
            }
        }
    }

}