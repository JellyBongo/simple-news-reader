package com.example.simplenewsreader.services

import com.example.simplenewsreader.Article
import java.util.Date

object ArticleService {
    fun getSampleArticle(): Article {
        return Article(
            "Some say the Devil is dead",
            "More say he rose again and joined the british army",
            "https://www.google.com",
            Date(),
            "JellyBongo",
            "https://i.kym-cdn.com/photos/images/facebook/001/255/479/85b.png"
        )
    }
}