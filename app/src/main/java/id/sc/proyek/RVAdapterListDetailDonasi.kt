package id.sc.proyek

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// adapter perlu mengextend class RecyclerView.Adapter<ViewHolder>
// viewholder digunakan sebagai penampung view dalam layout
// serta menggabungkan data dengan view
// view holder berada didalam class Adapter
class RVAdapterListDetailDonasi(
    private val listdonasi: ArrayList<DonasiEntity>,
    ): RecyclerView.Adapter<RVAdapterListDetailDonasi.CustomViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var itemView = LayoutInflater.from(parent.context)
        return CustomViewHolder(itemView.inflate(
            R.layout.detaildonasilayout, parent ,false
        ))
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val donasi = listdonasi[position]
        holder.tvdetaildonasiusername.text = donasi.username
        holder.tvdonasiuser.text = "Rp "+donasi.sumbangan.toString()





    }

    // digunakan untuk mengetahui ukuran dari list view yang akan di iterasikan
    override fun getItemCount(): Int {
        return listdonasi.size
    }

    class CustomViewHolder(view: View):RecyclerView.ViewHolder(view) {


        val tvdetaildonasiusername: TextView = itemView.findViewById(R.id.tvdetaildonasiusername)
        val tvdonasiuser: TextView = itemView.findViewById(R.id.tvdonasiuser)


    }

}