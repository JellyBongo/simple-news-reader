package com.example.simplenewsreader.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simplenewsreader.viewmodels.MainViewModel
import com.example.simplenewsreader.R
import com.example.simplenewsreader.Utilities
import com.example.simplenewsreader.adapters.ArticleAdapter
import com.prof.rssparser.Article
import kotlin.Comparator

class MainActivity : AppCompatActivity() {
    private var articleList = mutableListOf<Article>()

    /**
     * Compares articles by their pubDates
     */
    private class ArticlePubDateComparator : Comparator<Article> {
        override fun compare(o1: Article?, o2: Article?): Int {
            if (o1 == null || o2 == null)
                return 0
            if (o1.pubDate == null || o2.pubDate == null)
                return 0

            return Utilities.parseDate(o2.pubDate!!).compareTo(Utilities.parseDate(o1.pubDate!!))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.main_toolbar)) // Toolbar initialization

        resetLanguage() // Reset the language according to the shared preferences

        setupRecycleView()
    }

    /**
     * Initializes the recycle view and fills it with articles downloaded from some RSS feeds
     */
    private fun setupRecycleView() {
        val viewManager = LinearLayoutManager(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
        }

        val model = ViewModelProviders.of(this)[MainViewModel::class.java]

        model.getArticles().observe(this, Observer <List<Article>>{ articles ->
            if(articles != null){
                articleList.addAll(articles)
                articleList = sortedByDate(articleList).toMutableList()
                val newAdapter = ArticleAdapter(articleList.toTypedArray())
                recyclerView.adapter = newAdapter
                newAdapter.notifyDataSetChanged()
            }
        })
    }

    /**
     * Sets up the toolbar's contents
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar_menu, menu)
        return true
    }

    /**
     * Reacts on tapping the toolbar's icons
     */
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    /**
     * Sorts the list of articles by date and returns the sorted list
     */
    private fun sortedByDate(list: List<Article>): List<Article> {
        val articlePubDateComparator =
            ArticlePubDateComparator()
        return list.sortedWith(articlePubDateComparator)
    }

    /**
     * Resets the app's language according to the shared preferences
     */
    private fun resetLanguage() {
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val languagePreference = sp.getString("language", "")
        Utilities.changeLanguage(this, languagePreference)
    }
}
