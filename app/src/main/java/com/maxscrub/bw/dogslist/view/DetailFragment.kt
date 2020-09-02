package com.maxscrub.bw.dogslist.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.maxscrub.bw.dogslist.R
import com.maxscrub.bw.dogslist.databinding.FragmentDetailBinding
import com.maxscrub.bw.dogslist.databinding.FragmentDetailBindingImpl
import com.maxscrub.bw.dogslist.model.DogPalette
import com.maxscrub.bw.dogslist.util.getProgressDrawable
import com.maxscrub.bw.dogslist.util.loadImage
import com.maxscrub.bw.dogslist.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private var dogUUID = 0;
    private lateinit var dataBindingL: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBindingL = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBindingL.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // only if arguments not null
        arguments?.let {
            // retrieve argument passed from ListFragment
            dogUUID = DetailFragmentArgs.fromBundle(it).dogUUID
        }

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.fetch(dogUUID)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.dogLiveData.observe(this, Observer { dog ->
            dog?.let {
                dataBindingL.dog = dog

                it.imageUrl?.let {
                    setupBackgroundColor(it)
                }
            }
        })
    }

    private fun setupBackgroundColor(url: String) {
        Glide.with(this)
            .asBitmap().load(url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Palette.from(resource)
                        .generate { palette ->
                            // if rgb is null, color is 0 (default)
                            val intColor = palette?.vibrantSwatch?.rgb ?: 0
                            val myPalette = DogPalette(intColor)
                            dataBindingL.palatte = myPalette
                        }
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })
    }
}