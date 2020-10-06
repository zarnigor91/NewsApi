package com.example.marta.ui.dashboard

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marta.R
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_dashboard.*
import uk.co.imallan.jellyrefresh.JellyRefreshLayout
import uk.co.imallan.jellyrefresh.PullToRefreshLayout.PullToRefreshListener


class DashboardFragment :Fragment(R.layout.fragment_dashboard){
private lateinit var paymetAdapter: PaymetAdapter
    private var compositeDisposable = CompositeDisposable()
    private var mJellyLayout: JellyRefreshLayout? = null
    private var mMaxScrollSize = 0
    private val menu: Menu? = null
    private val PERCENTAGE_TO_SHOW_IMAGE = 20


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        paymetAdapter= PaymetAdapter(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_my_balance.text=RjexNumber.StringToString("1234234354")
        collapsing_toolbar.title=tv_my_balance.text

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
//            LayoutInflater.from(requireContext()).inflate(R.layout.fragment_dashboard, null)
//        mJellyLayout!!.setLoadingView(loadingView)
    val    mListener =
            OnOffsetChangedListener { _, verticalOffset ->
                if (mMaxScrollSize == 0) mMaxScrollSize = appbar.totalScrollRange
                val currentScrollPercentage: Int = (Math.abs(verticalOffset) * 100
                        / mMaxScrollSize)
                if (collapsing_toolbar.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(
                        collapsing_toolbar
                    )
                ) {

//                    val paddingDp = 10
//                    val density = context?.resources?.displayMetrics?.density
//                    val paddingPixel = (paddingDp * density!!).toInt()
//                    val lp = LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.WRAP_CONTENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT
//                    )
//                    lp.setMargins(0, 0, 0, paddingPixel)
//                    liner_valute.layoutParams = lp

//                    liner_valute.setPadding(0,0,0,paddingPixel);
                    ViewCompat.animate(liner_valute).scaleY(0.8f).scaleX(0.8f).start()
                    ViewCompat.animate(searchView).scaleY(0f).scaleX(0f).start()
                    ViewCompat.animate(img_down).scaleY(0f).scaleX(0f).start()
                    ViewCompat.animate(img_close).scaleY(0f).scaleX(0f).start()
                    ViewCompat.animate(tv_total).scaleY(0f).scaleX(0f).start()

                } else {
//                    val lp = LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.WRAP_CONTENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT
//                    )
//                    lp.setMargins(0, 0, 0, 0)
//                    liner_valute.layoutParams = lp
//                    liner_valute.setPadding(0,0,0,0);
                    ViewCompat.animate(searchView).scaleY(1f).scaleX(1f).start()
                    ViewCompat.animate(img_down).scaleY(1f).scaleX(1f).start()
                    ViewCompat.animate(img_close).scaleY(1f).scaleX(1f).start()
                    ViewCompat.animate(tv_total).scaleY(1f).scaleX(1f).start()
                    ViewCompat.animate(liner_valute).scaleY(1f).scaleX(1f).start()
                }
            }
        appbar.addOnOffsetChangedListener(mListener);
        mMaxScrollSize = appbar.totalScrollRange
    }

   fun initRv(){
       rv_paymet_card.layoutManager=
           LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
       rv_paymet_card.adapter=paymetAdapter
       rv_paymet_card.isNestedScrollingEnabled = false
       rv_paymet_mobile.layoutManager=LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
       rv_paymet_mobile.adapter=paymetAdapter
       rv_paymet_mobile.isNestedScrollingEnabled = false
       rv_paymet_servis.layoutManager=LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
       rv_paymet_servis.adapter=paymetAdapter
       rv_paymet_servis.isNestedScrollingEnabled = false
       rv_paymet_servis1.layoutManager=LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
       rv_paymet_servis1.adapter=paymetAdapter
       rv_paymet_servis1.isNestedScrollingEnabled = false
       rv_paymet_servis2.layoutManager=LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
       rv_paymet_servis2.adapter=paymetAdapter
       rv_paymet_servis2.isNestedScrollingEnabled = false
       rv_paymet_servis3.layoutManager=LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
       rv_paymet_servis3.adapter=paymetAdapter
       rv_paymet_servis3.isNestedScrollingEnabled = false
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


}