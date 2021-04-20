package com.maxscrub.bw.dogslist.view

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.maxscrub.bw.dogslist.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}