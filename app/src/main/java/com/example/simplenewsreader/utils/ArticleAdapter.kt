package com.example.simplenewsreader.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prof.rssparser.Article
import com.example.simplenewsreader.R
import kotlinx.android.synthetic.main.article_card.view.*
import java.text.SimpleDateFormat
import java.util.*

class ArticleAdapter(private val dataset: Array<Article>):
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>(){
    class ArticleViewHolder(val articleView: View) : RecyclerView.ViewHolder(articleView){
        init{}
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ArticleViewHolder {
        // create a new view
        val articleView = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_card, parent, false) as View
        return ArticleViewHolder(articleView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = dataset[position]
        val view = holder.articleView
        val basicDateFormat = SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.US)
        val displayDateFormat = SimpleDateFormat("HH:mm:ss, d MMM yyyy", Locale.US)

        if(article.pubDate != null){
            val date = basicDateFormat.parse(article.pubDate)
            val displayPubDate = displayDateFormat.format(date)
            view.pubDate.text = displayPubDate
        }
        else{
            view.pubDate.text = ""
        }

        view.title.text = article.title
        view.author.text = article.author

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataset.size
}