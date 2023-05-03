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
class RVAdapterRankinguser(
    private val listrank: ArrayList<Rank>,
    ): RecyclerView.Adapter<RVAdapterRankinguser.CustomViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var itemView = LayoutInflater.from(parent.context)
        return CustomViewHolder(itemView.inflate(
            R.layout.rankinguserslayout, parent ,false
        ))
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val rank = listrank[position]
        holder.tvnourut.text = (position+1).toString()

        holder.tvusername.text = rank.username
        holder.tvdonasi.text = rank.totaldonasi.toString()





    }

    // digunakan untuk mengetahui ukuran dari list view yang akan di iterasikan
    override fun getItemCount(): Int {
        return listrank.size
    }

    class CustomViewHolder(view: View):RecyclerView.ViewHolder(view) {

        val tvnourut: TextView = itemView.findViewById(R.id.tvnourut)

        val tvusername: TextView = itemView.findViewById(R.id.tvusernameuserranking)
        val tvdonasi: TextView = itemView.findViewById(R.id.tvtotaldonasiuserranking)



    }

}