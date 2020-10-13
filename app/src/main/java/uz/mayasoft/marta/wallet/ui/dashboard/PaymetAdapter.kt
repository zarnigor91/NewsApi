package uz.mayasoft.marta.wallet.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.mayasoft.marta.wallet.R
import kotlinx.android.synthetic.main.item_payment.view.*

class PaymetAdapter(context: Context):RecyclerView.Adapter<PaymetAdapter.ViewHolder>() {
    private var myList = mutableListOf<PaymeModel>()
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  view= inflater.inflate(R.layout.item_payment, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.onBind(myList[position])
    }

    fun update(newList: List<PaymeModel>) {
        myList.clear()
        myList.addAll(newList)
        this.notifyDataSetChanged()
    }
    class ViewHolder(contienerView: View):RecyclerView.ViewHolder(contienerView){
        fun onBind(paymeModel: PaymeModel) {
            itemView.apply {
                item_imag.setImageDrawable(paymeModel.image)
                item_tv.text=paymeModel.name.toString()
            }

        }

    }

}