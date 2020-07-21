package com.maxscrub.bw.dogslist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.maxscrub.bw.dogslist.R
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonDetails.setOnClickListener{
            val action = ListFragmentDirections.actionToDetailFrag()
            action.dogUUID = 5
            Navigation.findNavController(it).navigate(action)

            // navigation will handle swapping fragment
            // if res/navigation/dog_navigation is set up correctly

            // passing argument dogUUID = 5 to DetailFragment
        }
    }
}