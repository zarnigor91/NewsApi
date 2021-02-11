package com.example.newtest.app.feature.news.news

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newtest.R
import com.example.newtest.app.feature.news.NewsAdapter
import com.example.newtest.data.model.NewsItem
import kotlinx.android.synthetic.main.home_activity.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class NewsFragment @Inject constructor(
    provider: Provider<NewsPresenter>
) : MvpAppCompatFragment(), INewsView {

    private var adapter: NewsAdapter? = null
    private val presenter by moxyPresenter { provider.get() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        presenter.getNews()
    }

    fun init(){
        adapter= NewsAdapter(requireContext(),this::newsAction)
        rv_news_list.layoutManager = LinearLayoutManager(this.context)
        rv_news_list.adapter=adapter
    }
    fun newsAction(subject: NewsItem){

         findNavController().navigate(R.id.action_newsFragment_to_detalyNewsFragment)
    }

    override fun onNewsSuccess(news: List<NewsItem>) {
         adapter?.updateNews(news)
    }

    override fun onNewsError() {

    }

}