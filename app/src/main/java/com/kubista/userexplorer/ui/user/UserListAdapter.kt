package com.kubista.userexplorer.ui.user

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kubista.userexplorer.R
import com.kubista.userexplorer.databinding.ItemUserBinding
import com.kubista.userexplorer.model.user.User
import com.kubista.userexplorer.utils.SingleLiveData


class UserListAdapter : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    private lateinit var userList: List<User>
    private lateinit var context: Context
    var selectedUser: SingleLiveData<User> = SingleLiveData()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdapter.ViewHolder {
        context = parent.context
        val binding: ItemUserBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_user, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListAdapter.ViewHolder, position: Int) {
        holder.bind(userList[position])
        holder.itemView.setOnClickListener {
            selectedUser.sendAction(userList[position])
        }
    }

    override fun getItemCount(): Int {
        return if (::userList.isInitialized) userList.size else 0
    }

    fun updateUserList(userList: List<User>) {
        this.userList = userList

        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = UserViewModel()

        fun bind(user: User) {
            viewModel.bind(user)
            binding.viewModel = viewModel
        }
    }
}