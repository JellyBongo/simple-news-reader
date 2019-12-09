package com.example.simplenewsreader.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ScrollView
import androidx.fragment.app.FragmentActivity
import com.example.simplenewsreader.R
import com.example.simplenewsreader.fragments.SettingsFragment

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(findViewById(R.id.main_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setTitle(R.string.settings)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
    }

    private fun openSelectLanguageDialog() {
        // TODO
    }
}
