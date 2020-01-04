package com.example.sadok.album.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.sadok.album.R
import com.example.sadok.album.databinding.AlbumListFragmentBinding
import kotlinx.android.synthetic.main.large_image_view.view.*

class AlbumListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding:AlbumListFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.album_list_fragment, container, false)

        val application = requireNotNull(this.activity).application


        val viewModelFactory = AlbumListViewModelFactory(application)

        val viewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(AlbumListViewModel::class.java)

        binding.viewModel = viewModel

        binding.lifecycleOwner = this
        val adapter = SongAdapter(SongClick {
            displayLargeImageDialog(it)
        })
        binding.recyclerView.adapter = adapter

        viewModel.songList.observe(viewLifecycleOwner, Observer {
            it?.apply {
                adapter.submitList(it)
            }
        })
        return binding.root
    }

    private fun displayLargeImageDialog(imageUrl: String) {
        val dialog = AlertDialog.Builder(context)
        val factory = LayoutInflater.from(context)
        val view: View = factory.inflate(R.layout.large_image_view, null)
        Glide.with(this).load(imageUrl).into(view.myZoomageView)
        dialog.setView(view)
        dialog.show()
    }


}
