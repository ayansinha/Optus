package org.techm.optus.ui.view.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import org.techm.optus.R
import org.techm.optus.data.model.user.UserModel
import org.techm.optus.data.network.APIBuilder
import org.techm.optus.data.network.APIServiceImpl
import org.techm.optus.data.repository.UserRepository
import org.techm.optus.databinding.FragmentUserBinding
import org.techm.optus.ui.adapter.user.UserAdapter
import org.techm.optus.ui.factory.user.UserViewModelFactory
import org.techm.optus.ui.viewmodel.user.UserViewModel
import org.techm.optus.util.Constants.Companion.ERROR_MSG
import org.techm.optus.util.Constants.Companion.ID
import org.techm.optus.util.Constants.Companion.NO_CONNECTION
import org.techm.optus.util.Status
import org.techm.optus.util.isConnection
import org.techm.optus.util.showSnackBar


/**
 * @fragment{FragmentUser}
 */
@Suppress("DEPRECATION")
class FragmentUser : Fragment(), UserAdapter.OnItemClickListener {

    private lateinit var userViewModel: UserViewModel
    private lateinit var binding: FragmentUserBinding
    private lateinit var userFactory: UserViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val title: String = getString(R.string.user_info)
        binding.mToolbarUserTitle.text = title
        userFactory = UserViewModelFactory(APIServiceImpl(APIBuilder.apiService))
        userViewModel =
            ViewModelProviders.of(this@FragmentUser, userFactory)
                .get(UserViewModel::class.java)

        /**
         * check connectivity
         */
        if (activity?.isConnection()!!) {
            setUpAPICall()
        } else {
            binding.recyclerViewUser.showSnackBar(NO_CONNECTION)
        }
    }

    /**
     * setup view-model and fetch data for user and display it in recyclerview
     **/
    private fun setUpAPICall() {
        userViewModel.getUserList().observe(viewLifecycleOwner, Observer {
            it.let { result ->
                when (result.status) {

                    Status.LOADING -> {
                        binding.progressBarUser.visibility = View.VISIBLE
                        binding.recyclerViewUser.visibility = View.GONE
                    }

                    Status.SUCCESS -> {
                        binding.recyclerViewUser.visibility = View.VISIBLE
                        binding.progressBarUser.visibility = View.GONE/**/
                        result.data.let { user ->
                            binding.recyclerViewUser.setHasFixedSize(true)
                            binding.recyclerViewUser.adapter =
                                user?.let { userList ->
                                    UserAdapter(
                                        userList,
                                        this,
                                        requireContext()
                                    )
                                }
                        }
                    }

                    Status.ERROR -> {
                        binding.recyclerViewUser.visibility = View.VISIBLE
                        binding.progressBarUser.visibility = View.GONE
                        binding.recyclerViewUser.showSnackBar(ERROR_MSG)
                    }
                }
            }
        })
    }
    /**
     * on item click of recyclerview
     */
    override fun onItemClick(item: UserModel?) {
        val bundle = bundleOf(ID to item?.id)
        findNavController().navigate(R.id.action_fragmentUser_to_fragmentAlbum, bundle)
    }
}
