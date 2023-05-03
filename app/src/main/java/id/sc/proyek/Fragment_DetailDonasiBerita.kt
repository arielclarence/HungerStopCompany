package id.sc.proyek

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Fragment_DetailDonasiBerita(
    var headline: String,
     var adapterdonasi: RVAdapterListDetailDonasi,
) : Fragment() {
    private lateinit var RVdonasi: RecyclerView
    private lateinit var txtHeadlineDetailBerita: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        txtHeadlineDetailBerita = view.findViewById(R.id.txtHeadlineDetailBerita)
        txtHeadlineDetailBerita.setText("List donasi Berita "+headline)
        RVdonasi = view.findViewById(R.id.RVdonasi)
        setLayoutManager(view)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_donasi_berita, container, false)
    }

    private fun setLayoutManager(view: View){

        val verticalLayoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL,false)

        RVdonasi.adapter = adapterdonasi
        RVdonasi.layoutManager= verticalLayoutManager
    }

}