package com.kubista.userexplorer.ui.user

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kubista.userexplorer.R
import com.kubista.userexplorer.base.viewModelFactory
import com.kubista.userexplorer.databinding.FragmentUserListBinding
import com.kubista.userexplorer.injection.component.DaggerViewInjector
import com.kubista.userexplorer.injection.module.AppModule
import com.kubista.userexplorer.injection.module.DatabaseModule
import com.kubista.userexplorer.model.user.UserDao
import com.kubista.userexplorer.model.user.UserRepository
import com.kubista.userexplorer.ui.detail.UserDetailActivity
import com.kubista.userexplorer.utils.KEY_USER_PARCEL
import javax.inject.Inject


class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding
    private lateinit var viewModel: UserListViewModel
    private var errorSnackbar: Snackbar? = null

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // val db = Room.databaseBuilder(context!!, AppDatabase::class.java, "users").build()
        DaggerViewInjector
                .builder()
                .appModule(AppModule(activity!!.application))
                .databaseModule(DatabaseModule(activity!!.application))
                .build()
                .inject(this)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory { UserListViewModel(savedInstanceState, userRepository, userDao) }).get(UserListViewModel::class.java)
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_user_list, container, false)

        val myView: View = binding.root

        binding.viewModel = viewModel
        binding.userList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

        viewModel.userListAdapter.selectedUser.observe(this, Observer { user ->
            val intent = Intent(activity, UserDetailActivity::class.java)
            intent.putExtra(KEY_USER_PARCEL, user!!.getParcelable())
            startActivity(intent)
        })

        return myView
    }
}