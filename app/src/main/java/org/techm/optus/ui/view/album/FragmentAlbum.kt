package org.techm.optus.ui.view.album

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import org.techm.optus.R
import org.techm.optus.data.model.album.AlbumModel
import org.techm.optus.data.network.APIBuilder
import org.techm.optus.data.network.APIServiceImpl
import org.techm.optus.data.repository.AlbumRepository
import org.techm.optus.databinding.FragmentAlbumBinding
import org.techm.optus.ui.adapter.album.AlbumAdapter
import org.techm.optus.ui.factory.album.AlbumViewModelFactory
import org.techm.optus.ui.viewmodel.album.AlbumViewModel
import org.techm.optus.util.Constants.Companion.ALBUM_ID
import org.techm.optus.util.Constants.Companion.ERROR_MSG
import org.techm.optus.util.Constants.Companion.ID
import org.techm.optus.util.Constants.Companion.NO_CONNECTION
import org.techm.optus.util.Constants.Companion.PHOTO_ID
import org.techm.optus.util.Constants.Companion.TITLE
import org.techm.optus.util.Constants.Companion.URL
import org.techm.optus.util.Status
import org.techm.optus.util.isConnection
import org.techm.optus.util.showSnackBar


/**
 * @fragment{FragmentAlbum}
 */
class FragmentAlbum : Fragment(), AlbumAdapter.OnImageClickListener {

    private lateinit var albumViewModel: AlbumViewModel
    private lateinit var binding: FragmentAlbumBinding
    private var userID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userID = arguments?.getInt(ID)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.mToolbarAlbumTitle.text = "Album ID: $userID"
        albumViewModel = ViewModelProviders.of(this , AlbumViewModelFactory(APIServiceImpl(APIBuilder.apiService))).get(AlbumViewModel::class.java)

        if (activity?.isConnection()!!) {
            setUpAPICall()
        }else {
            binding.recyclerViewAlbum.showSnackBar(NO_CONNECTION)
        }

    }

    private fun setUpAPICall() {

        albumViewModel.getAlbumList(userID).observe(viewLifecycleOwner, Observer {
            it.let { result ->
                when (result.status) {

                    Status.LOADING -> {
                        binding.progressBarAlbum.visibility = View.VISIBLE
                        binding.recyclerViewAlbum.visibility = View.GONE

                    }

                    Status.SUCCESS -> {
                        binding.recyclerViewAlbum.visibility = View.VISIBLE
                        binding.progressBarAlbum.visibility = View.GONE
                        result.data.let { album ->
                            binding.recyclerViewAlbum.setHasFixedSize(true)
                            binding.recyclerViewAlbum.adapter =
                                album?.let { albumList -> AlbumAdapter(albumList, this) }
                        }
                    }

                    Status.ERROR -> {
                        binding.recyclerViewAlbum.visibility = View.VISIBLE
                        binding.progressBarAlbum.visibility = View.GONE
                        binding.recyclerViewAlbum.showSnackBar(ERROR_MSG)
                    }
                }

            }
        })
    }

    override fun onItemClick(item: AlbumModel?) {

        val bundle = bundleOf(
            ALBUM_ID to item?.albumId,
            PHOTO_ID to item?.id,
            TITLE to item?.title,
            URL to item?.url
        )

        findNavController().navigate(R.id.action_fragmentAlbum_to_fragmentAlbumDetails , bundle)
    }


}
