package com.maxscrub.bw.dogslist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.maxscrub.bw.dogslist.R
import com.maxscrub.bw.dogslist.databinding.FragmentDetailBinding
import com.maxscrub.bw.dogslist.databinding.FragmentDetailBindingImpl
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
            }
        })
    }
}