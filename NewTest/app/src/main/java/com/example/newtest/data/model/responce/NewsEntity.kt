package com.example.newtest.data.model.responce

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newtest.data.model.NewsItem

@Entity
 class NewsEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "status")
    var status: String? = null
    @ColumnInfo(name = "copyright")
    var copyright: String? = null
    @ColumnInfo(name = "section")
    var section: String? = null
    @ColumnInfo(name = "last_updated")
    var lastUpdated: String? = null
    @ColumnInfo(name = "num_results")
    var numResults: Long? = null
    @ColumnInfo(name = "results")
    var newsItems: List<NewsItem>? = null
}