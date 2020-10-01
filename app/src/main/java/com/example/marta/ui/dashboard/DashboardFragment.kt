package com.example.marta.ui.dashboard

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marta.R
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment :Fragment(R.layout.fragment_dashboard){
private lateinit var paymetAdapter: PaymetAdapter
    private var compositeDisposable = CompositeDisposable()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        paymetAdapter= PaymetAdapter(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_my_balance.text=RjexNumber.StringToString("12342343543543")

         initRv()
        if (paymeList()!=null)
        { paymetAdapter.update(paymeList())}
        else
            Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
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
    fun paymeList():List<PaymeModel>{
        val list= arrayListOf<PaymeModel>()
        list.add(PaymeModel(ContextCompat.getDrawable(requireContext(),R.drawable.ums),"ums"))
        list.add(PaymeModel(ContextCompat.getDrawable(requireContext(),R.drawable.beeline),"beeline"))
        list.add(PaymeModel(ContextCompat.getDrawable(requireContext(),R.drawable.ic_histroy),"payme"))
        list.add(PaymeModel(ContextCompat.getDrawable(requireContext(),R.drawable.ic_histroy),"histroy"))
        return list
    }
}