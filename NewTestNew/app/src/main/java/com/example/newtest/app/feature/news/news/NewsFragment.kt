package com.example.newtest.app.feature.news.news

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newtest.R
import com.example.newtest.app.feature.news.NewsAdapter
import com.example.newtest.data.model.NewsItem
import com.example.newtest.data.view_model.SharedViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.home_activity.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.util.Observer
import javax.inject.Inject

class NewsFragment : MvpAppCompatFragment(), INewsView {

    private var adapter: NewsAdapter? = null
//    private var db :NewsDb?=null
    private var model: SharedViewModel?=null

    @Inject
    lateinit var provider: NewsPresenter
    private val presenter by moxyPresenter { provider }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRes()
        presenter.getNews()
//         db = NewsDb.getAppDatabase(requireContext())
//        val list = db?.getNewsDao()?.getAllCard()
    }

    fun initRes() {
        adapter = NewsAdapter(requireContext(), this::newsAction)
        rv_news_list.layoutManager = LinearLayoutManager(this.context)
        rv_news_list.adapter = adapter
    }

    fun initDb() {
        adapter = NewsAdapter(requireContext(), this::newsAction)
        rv_news_list.layoutManager = LinearLayoutManager(this.context)
        rv_news_list.adapter = adapter
    }

    fun newsAction(subject: NewsItem) {
//        model= ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)
//        model?.itemNews?.observe(viewLifecycleOwner,
//            Obserhoziver<Int> { o ->
//                Log.wtf("qrbalanceObserve", "$o")
//                Log.wtf("paymentCardSelectionAdapter", ""+adapter.setProvidedPrice(o))
//            })
        findNavController().navigate(R.id.action_newsFragment_to_detalyNewsFragment)

    }

    override fun onNewsSuccess(news: List<NewsItem>) {
        adapter?.updateNews(news)
        Log.wtf("NewsFragmentSucsses", "$news")
//        db?.getNewsDao()?.insertNews(news)
    }

    override fun onNewsError() {

    }

}