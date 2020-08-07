package com.maxscrub.bw.dogslist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.maxscrub.bw.dogslist.R

class DetailFragment : Fragment() {

    private var dogUUID = 0;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // only if arguments not null
        arguments?.let {
            // retrieve argument passed from ListFragment
            dogUUID = DetailFragmentArgs.fromBundle(it).dogUUID
        }
    }
}