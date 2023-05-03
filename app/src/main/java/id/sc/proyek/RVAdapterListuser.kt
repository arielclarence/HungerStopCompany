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
class RVAdapterListuser(
    private val listuser: ArrayList<UserEntity>,
    ): RecyclerView.Adapter<RVAdapterListuser.CustomViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var itemView = LayoutInflater.from(parent.context)
        return CustomViewHolder(itemView.inflate(
            R.layout.userslayout, parent ,false
        ))
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val user = listuser[position]
        holder.tvusernameuser.text = user.username
        holder.tvdisplaynameuser.text = user.displayname





    }

    // digunakan untuk mengetahui ukuran dari list view yang akan di iterasikan
    override fun getItemCount(): Int {
        return listuser.size
    }

    class CustomViewHolder(view: View):RecyclerView.ViewHolder(view) {


        val tvusernameuser: TextView = itemView.findViewById(R.id.tvusernameuser)
        val tvdisplaynameuser: TextView = itemView.findViewById(R.id.tvdisplaynameuser)


    }

}