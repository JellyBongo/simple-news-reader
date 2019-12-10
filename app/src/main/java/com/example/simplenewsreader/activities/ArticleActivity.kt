package com.example.simplenewsreader.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.example.simplenewsreader.R
import com.example.simplenewsreader.Utilities
import com.example.simplenewsreader.adapters.ARTICLE_PARAMS
import kotlinx.android.synthetic.main.activity_article.view.*

class ArticleActivity : AppCompatActivity() {
    private var linkToSource = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        setSupportActionBar(findViewById(R.id.article_toolbar)) // Toolbar initialization
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Get extra parameters from the intent
        val articleParams = intent.getSerializableExtra(ARTICLE_PARAMS) as HashMap<String, String>

        fillViewWithArticleParams(articleParams)
        removeEmptyViews()
    }

    /**
     * Sets up the article view according to the extra parameters from the intent
     */
    private fun fillViewWithArticleParams (params: HashMap<String, String>) {
        val title = findViewById<TextView>(R.id.title)
        val description = findViewById<TextView>(R.id.description)
        val pubDate = findViewById<TextView>(R.id.pubDate)
        val author = findViewById<TextView>(R.id.author)

        title.text = params["title"]
        description.text = params["description"]
        pubDate.text = Utilities.prettifyDate(Utilities.parseDate(params["pubDate"]!!))
        author.text = params["author"]

        linkToSource = params["link"]?: ""
    }

    fun onLinkClick(view: View){
        openLinkInBrowser()
    }

    private fun openLinkInBrowser() {
        if(linkToSource.isNotEmpty()) {
            val openLinkIntent = Intent(Intent.ACTION_VIEW, Uri.parse(linkToSource))
            startActivity(openLinkIntent)
        }
        else{
            // TODO: error message box
        }
    }

    /**
     * Clears some space by removing unnecessary views
     */
    private fun removeEmptyViews() {
        val layout = findViewById<LinearLayout>(R.id.articleLayout)
        layout.children.forEach { view ->
            if(view.id != R.id.article_toolbar && (view as TextView).text.isNullOrBlank())
                view.visibility = View.GONE
        }
    }
}
