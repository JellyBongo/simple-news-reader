package com.example.simplenewsreader.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simplenewsreader.viewmodels.MainViewModel
import com.example.simplenewsreader.R
import com.example.simplenewsreader.adapters.ArticleAdapter
import com.prof.rssparser.Article
import java.text.SimpleDateFormat
import java.util.*
import kotlin.Comparator

/* TODO: transform the app to single-activity structure: develop article- and article-list fragments.
    Transfer all code related to the recyclerView to the article-list fragment.
    Work out navigation, languages and a settings page (probably will be a fragment too)*/

class MainActivity : AppCompatActivity() {
    private var articleList = mutableListOf<Article>()

    private class ArticlePubDateComparator : Comparator<Article> {
        override fun compare(o1: Article?, o2: Article?): Int {
            if (o1 == null || o2 == null)
                return 0
            if (o1.pubDate == null || o2.pubDate == null)
                return 0

            val dataFormat = SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.US)
            return dataFormat.parse(o2.pubDate).compareTo(dataFormat.parse(o1.pubDate))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    private fun sortedByDate(list: List<Article>): List<Article> {
        val articlePubDateComparator =
            ArticlePubDateComparator()
        return list.sortedWith(articlePubDateComparator)
    }
}
