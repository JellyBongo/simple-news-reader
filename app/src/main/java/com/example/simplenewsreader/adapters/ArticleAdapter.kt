package com.example.simplenewsreader.adapters


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.prof.rssparser.Article
import com.example.simplenewsreader.R
import com.example.simplenewsreader.Utilities
import com.example.simplenewsreader.activities.ArticleActivity
import kotlinx.android.synthetic.main.article_card.view.*
import java.text.SimpleDateFormat
import java.util.*

const val ARTICLE_PARAMS = "com.example.simplenewsreader.adapters.ARTICLE_PARAMS"

class ArticleAdapter(private val dataset: Array<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(val articleView: View, private val articleArray: Array<Article>) :
        RecyclerView.ViewHolder(articleView), View.OnClickListener {

        override fun onClick(view: View?) {
            val position = adapterPosition
            val clickedArticle = articleArray[position]

            val articleParams = hashMapOf(
                "title" to clickedArticle.title,
                "description" to clickedArticle.description,
                "pubDate" to clickedArticle.pubDate,
                "author" to clickedArticle.author,
                "link" to clickedArticle.link,
                "image" to clickedArticle.image,
                "content" to clickedArticle.content,
                "categories" to clickedArticle.categories,
                "guid" to clickedArticle.guid
            )

            val intent = Intent(articleView.context, ArticleActivity::class.java).apply {
                putExtra(ARTICLE_PARAMS, articleParams)
            }

            articleView.context.startActivity(intent)
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        // create a new view
        val articleView = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_card, parent, false) as View
        return ArticleViewHolder(articleView, dataset)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = dataset[position]
        val view = holder.articleView

        if (article.pubDate != null) {
            val date = Utilities.parseDate(article.pubDate!!)
            val displayPubDate = Utilities.prettifyDate(date)
            view.pubDate.text = displayPubDate
        } else {
            view.pubDate.text = ""
        }

        view.title.text = article.title
        view.author.text = article.author

        view.setOnClickListener(holder)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataset.size
}