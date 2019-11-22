package com.example.simplenewsreader.utils

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simplenewsreader.Article
import com.example.simplenewsreader.R
import kotlinx.android.synthetic.main.article_card.view.*

class ArticleAdapter(private val dataset: Array<Article>):
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>(){
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class ArticleViewHolder(val articleView: View) : RecyclerView.ViewHolder(articleView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ArticleAdapter.ArticleViewHolder {
        // create a new view
        val articleView = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_card, parent, false) as View
        return ArticleViewHolder(articleView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataset.size
}