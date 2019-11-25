package com.example.simplenewsreader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simplenewsreader.utils.ArticleAdapter
import com.prof.rssparser.Article

class MainActivity : AppCompatActivity() {
    private val articleList = mutableListOf<Article>()

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
                val newAdapter = ArticleAdapter(articleList.toTypedArray())
                recyclerView.adapter = newAdapter
                newAdapter.notifyDataSetChanged()
            }
        })
    }
}
