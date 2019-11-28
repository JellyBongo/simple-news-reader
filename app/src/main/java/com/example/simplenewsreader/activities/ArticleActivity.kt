package com.example.simplenewsreader.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.simplenewsreader.R
import com.example.simplenewsreader.adapters.ARTICLE_PARAMS
import kotlinx.android.synthetic.main.activity_article.view.*

class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val articleParams = intent.getSerializableExtra(ARTICLE_PARAMS) as HashMap<String, String>

        fillViewWithArticleParams(articleParams)
    }

    private fun fillViewWithArticleParams (params: HashMap<String, String>) {
        val title = findViewById<TextView>(R.id.title)
        val description = findViewById<TextView>(R.id.description)
        val pubDate = findViewById<TextView>(R.id.pubDate)
        val author = findViewById<TextView>(R.id.author)

        title.text = params["title"]
        description.text = params["description"]
        pubDate.text = params["pubDate"]
        author.text = params["author"]
    }
}
