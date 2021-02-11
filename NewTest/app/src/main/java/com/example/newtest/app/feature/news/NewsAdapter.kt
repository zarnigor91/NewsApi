package com.example.newtest.app.feature.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newtest.R
import com.example.newtest.data.model.NewsItem
import com.example.newtest.data.model.NewsModel
import kotlinx.android.synthetic.main.news_item.view.*


class NewsAdapter(context: Context, private val action: (NewsItem) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private var myList = mutableListOf<NewsItem>()
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.news_item, parent, false)
        return ViewHolder(view, action)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(myList[position])
    }

    fun updateNews(newList: List<NewsItem>) {
        myList.clear()
        myList.addAll(newList)
        this.notifyDataSetChanged()
    }

    class ViewHolder(contienerView: View, private val action: (NewsItem) -> Unit) :
        RecyclerView.ViewHolder(contienerView) {
        private var selectSubject: NewsItem? = null

        fun onBind(newsItem: NewsItem) {
            this.selectSubject = newsItem

            itemView.apply {
                setOnClickListener { action.invoke(selectSubject!!) }

                Glide.with(itemView)
                    .load(newsItem)
                    .centerCrop()
                    .error(R.drawable.newss)
                    .into(iv_news_item_image)
                tv_title.text = newsItem.title
                tv_caption.text =newsItem.abstract
            }

        }

    }

}