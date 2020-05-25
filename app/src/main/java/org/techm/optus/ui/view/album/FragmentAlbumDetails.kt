package org.techm.optus.ui.view.album

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.header_view.view.*
import org.techm.optus.databinding.FragmentAlbumDetailsBinding

class FragmentAlbumDetails: Fragment() {

    private lateinit var binding: FragmentAlbumDetailsBinding
    private var albumID = 0
    private var photoID = 0
    private var title = ""
    private var url = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAlbumDetailsBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        albumID = arguments?.getInt("albumID")!!
        photoID = arguments?.getInt("photoID")!!
        title = arguments?.getString("title")!!
        url = arguments?.getString("url")!!
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.toolBarHeaderView.textViewAlbumDetailID.text = "Album ID : $albumID"
        binding.toolBarHeaderView.textViewPhotoDetailID.text = "Photo ID : $photoID"
        binding.textViewAlbumDetailTitle.text = title
        Picasso.get().load(url).into(binding.imageViewAlbumImage)
    }
}