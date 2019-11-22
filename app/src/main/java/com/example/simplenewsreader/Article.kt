package com.example.simplenewsreader

import java.util.Date

data class Article (val title: String,
                    val description: String,
                    val link: String,
                    val pubDate: Date,
                    val author: String,
                    val imgSource: String)