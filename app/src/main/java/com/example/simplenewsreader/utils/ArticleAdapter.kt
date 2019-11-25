package com.example.simplenewsreader.utils

import android.graphics.drawable.Drawable
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prof.rssparser.Article
import com.example.simplenewsreader.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_card.view.*
import java.io.InputStream
import java.net.URL

class ArticleAdapter(private val dataset: Array<Article>):
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>(){
    class ArticleViewHolder(val articleView: View) : RecyclerView.ViewHolder(articleView)

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

        view.title.text = article.title
        view.description.text = article.description
        view.pubDate.text = article.pubDate
        view.author.text = article.author

       /* view.image.apply {
            setImageResource(R.drawable.dogesmall)
            adjustViewBounds = true
        }*/
        Picasso.get().load(article.image).into(view.image)
        view.image.adjustViewBounds = true
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataset.size
}