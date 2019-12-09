package com.example.simplenewsreader.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

import com.example.simplenewsreader.R


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_screen, rootKey)
    }

}
