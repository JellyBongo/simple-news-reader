package com.example.simplenewsreader.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prof.rssparser.Article
import com.prof.rssparser.Parser
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val articles: MutableLiveData<List<Article>> by lazy {
        MutableLiveData<List<Article>>().also {
            loadArticles()
        }
    }

    fun getArticles(): LiveData<List<Article>> {
        return articles
    }

    private fun addToArticles(articleList: MutableList<Article>) {
        articles.postValue(articleList)
    }

    /**
     * Gets articles from the rss feeds
     */
    private fun loadArticles() {
        val urls = getUrls()
        urls.forEach { url ->
            coroutineScope.launch(Dispatchers.Main) {
                try {
                    val parser = Parser()
                    val articleList = parser.getArticles(url)
                    addToArticles(articleList)
                } catch (e: Exception) {
                    println(e.message)
                }
            }
        }
    }

    private fun getUrls(): Array<String> {
        return arrayOf(
            "http://lenta.ru/rss",
            "http://meduza.io/rss/all",
            "http://static.feed.rbc.ru/rbc/logical/footer/news.rss"
        )
    }

}