package com.example.simplenewsreader.fragments

import android.content.Intent
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat

import com.example.simplenewsreader.R
import com.example.simplenewsreader.Utilities
import com.example.simplenewsreader.activities.SettingsActivity


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_screen, rootKey)

        // Changing the app's locale when user chooses another language in settings
        val languagePreference = findPreference<ListPreference>("language")
        languagePreference?.setOnPreferenceChangeListener { _, newLanguage ->
            Utilities.changeLanguage(context, newLanguage as String)
            val intent = Intent(context, SettingsActivity::class.java)
            context?.startActivity(intent)
            true
        }

    }
}
