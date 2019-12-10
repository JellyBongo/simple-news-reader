package com.example.simplenewsreader.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simplenewsreader.R
import com.example.simplenewsreader.fragments.SettingsFragment

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setTitle(R.string.settings)
        supportFragmentManager // Adding the Settings Fragment
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()

        setSupportActionBar(findViewById(R.id.settings_toolbar)) // Toolbar initialization
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


}
