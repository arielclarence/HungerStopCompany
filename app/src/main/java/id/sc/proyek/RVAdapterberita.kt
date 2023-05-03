package id.sc.proyek

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// adapter perlu mengextend class RecyclerView.Adapter<ViewHolder>
// viewholder digunakan sebagai penampung view dalam layout
// serta menggabungkan data dengan view
// view holder berada didalam class Adapter
class RVAdapterberita(
    private val listdonasi: ArrayList<DonasiEntity>,


    private val listberita: ArrayList<BeritaEntity>,
    private val detailsumbanganonClickListener: (Headline:String)->Unit,

    ): RecyclerView.Adapter<RVAdapterberita.CustomViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var itemView = LayoutInflater.from(parent.context)
        return CustomViewHolder(itemView.inflate(
            R.layout.beritaslayout, parent ,false
        ))
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val berita = listberita[position]
        holder.tvheadlineberita.text = berita.headline
        var donasiterkumpul=0
        for (donasi in listdonasi){
            if (donasi.headline==berita.headline){
                donasiterkumpul+=donasi.sumbangan
            }
        }
        holder.tvketeranganberita.text = berita.keterangan
        holder.tvtargetsumbangan.text = "Goals: Rp"+donasiterkumpul+"/Rp"+berita.jumlahsumbangan
        holder.tvpembuatberita.text = berita.pembuat
        holder.ivdetailsumbangan.setOnClickListener {
            detailsumbanganonClickListener(berita.headline)
        }

    }

    // digunakan untuk mengetahui ukuran dari list view yang akan di iterasikan
    override fun getItemCount(): Int {
        return listberita.size
    }

    class CustomViewHolder(view: View):RecyclerView.ViewHolder(view) {

        val tvketeranganberita: TextView = itemView.findViewById(R.id.tvketerangan)
        val tvtargetsumbangan: TextView = itemView.findViewById(R.id.tvtargetsumbangan)
        val tvheadlineberita: TextView = itemView.findViewById(R.id.tvheadline)
        val tvpembuatberita: TextView = itemView.findViewById(R.id.tvpembuatberita)
        val ivdetailsumbangan: ImageView = itemView.findViewById(R.id.ivdetailsumbangan)



    }

}