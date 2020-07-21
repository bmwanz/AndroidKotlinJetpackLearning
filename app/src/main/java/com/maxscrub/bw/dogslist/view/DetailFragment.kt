package com.maxscrub.bw.dogslist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.maxscrub.bw.dogslist.R
import kotlinx.android.synthetic.main.fragment_detail.*

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
            detailFragText.text = dogUUID.toString()
        }

        buttonList.setOnClickListener{
            val action = DetailFragmentDirections.actionToListFrag()
            Navigation.findNavController(it).navigate(action)

            // navigation will handle swapping fragment
            // if res/navigation/dog_navigation is set up correctly
        }
    }
}