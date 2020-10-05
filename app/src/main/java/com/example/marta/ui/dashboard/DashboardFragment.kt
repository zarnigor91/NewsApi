package com.example.marta.ui.dashboard

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marta.R
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.chip.ChipGroup
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_dashboard.*
import uk.co.imallan.jellyrefresh.JellyRefreshLayout
import uk.co.imallan.jellyrefresh.PullToRefreshLayout.PullToRefreshListener


class DashboardFragment :Fragment(R.layout.fragment_dashboard),ChipGroup.OnCheckedChangeListener{
private lateinit var paymetAdapter: PaymetAdapter
    private var compositeDisposable = CompositeDisposable()
    private var mJellyLayout: JellyRefreshLayout? = null

    private val menu: Menu? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        paymetAdapter= PaymetAdapter(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_my_balance.text=RjexNumber.StringToString("12342343543543")
        collapsing_toolbar.title=liner_balance.toString()

         initRv()
        if (paymeList()!=null)
        { paymetAdapter.update(paymeList())}
        else
            Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()

        mJellyLayout = view.findViewById(R.id.jelly_refresh) as JellyRefreshLayout
        mJellyLayout!!.setPullToRefreshListener(PullToRefreshListener { pullToRefreshLayout ->
            pullToRefreshLayout.postDelayed(
                { mJellyLayout!!.isRefreshing =  false },
                1000
            )
        })
//        val loadingView: View =
//            LayoutInflater.from(requireContext()).inflate(R.layout.view_loading, null)
//        mJellyLayout!!.setLoadingView(loadingView)
    val    mListener =
            OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (collapsingToolbar.getHeight() + verticalOffset < 2 * ViewCompat.getMinimumHeight(
                        collapsingToolbar
                    )
                ) {
                    hello.animate().alpha(1).setDuration(600)
                } else {
                    hello.animate().alpha(0).setDuration(600)
                }
            }


    }

   fun initRv(){
       rv_paymet_card.layoutManager=
           LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
       rv_paymet_card.adapter=paymetAdapter
       rv_paymet_mobile.layoutManager=LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
       rv_paymet_mobile.adapter=paymetAdapter
       rv_paymet_servis.layoutManager=LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
       rv_paymet_servis.adapter=paymetAdapter
    }
    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
    private fun paymeList():List<PaymeModel>{
        val list= arrayListOf<PaymeModel>()
        list.add(PaymeModel(ContextCompat.getDrawable(requireContext(),R.drawable.ums),"ums"))
        list.add(PaymeModel(ContextCompat.getDrawable(requireContext(),R.drawable.beeline),"beeline"))
        list.add(PaymeModel(ContextCompat.getDrawable(requireContext(),R.drawable.ic_histroy),"payme"))
        list.add(PaymeModel(ContextCompat.getDrawable(requireContext(),R.drawable.ic_histroy),"histroy"))
        return list
    }

    override fun onCheckedChanged(group: ChipGroup?, checkedId: Int) {
        TODO("Not yet implemented")
    }
}