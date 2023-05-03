package id.sc.proyek

import android.media.Image
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
class RVAdapterberitauser(

    private val listdonasi: ArrayList<DonasiEntity>,

    private val listberita: ArrayList<BeritaEntity>,
    private val sumbangonClickListener: (Headline:String,Sisasumbangan:Int)->Unit,
    ): RecyclerView.Adapter<RVAdapterberitauser.CustomViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var itemView = LayoutInflater.from(parent.context)
        return CustomViewHolder(itemView.inflate(
            R.layout.beritasuserlayout, parent ,false
        ))
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val berita = listberita[position]
        holder.tvheadlineberita.text = berita.headline

        holder.tvketeranganberita.text = berita.keterangan
        var donasiterkumpul=0
        for (donasi in listdonasi){
            if (donasi.headline==berita.headline){
                donasiterkumpul+=donasi.sumbangan
            }
        }

        holder.tvtargetsumbangan.text = "Goals: Rp"+donasiterkumpul+"/Rp"+berita.jumlahsumbangan

        holder.tvpembuatberita.text = berita.pembuat

        if (donasiterkumpul==berita.jumlahsumbangan)
        {
            holder.ivaddsumbangan.setImageResource(R.drawable.ic_baseline_check_24)
        }
        else{
            holder.ivaddsumbangan.setOnClickListener {
                sumbangonClickListener(berita.headline,berita.jumlahsumbangan-donasiterkumpul)
            }

        }


    }



    // digunakan untuk mengetahui ukuran dari list view yang akan di iterasikan
    override fun getItemCount(): Int {
        return listberita.size
    }

    class CustomViewHolder(view: View):RecyclerView.ViewHolder(view) {

        val tvketeranganberita: TextView = itemView.findViewById(R.id.tvketeranganuser)
        val tvtargetsumbangan: TextView = itemView.findViewById(R.id.tvtargetsumbanganuser)
        val tvheadlineberita: TextView = itemView.findViewById(R.id.tvheadlineuser)
        val tvpembuatberita: TextView = itemView.findViewById(R.id.tvpembuatberitauser)
        val ivaddsumbangan: ImageView = itemView.findViewById(R.id.ivaddsumbangan)


    }

}