package id.sc.proyek

import android.os.Bundle
import android.system.Os
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import id.sc.proyek.databinding.ActivityMainBinding
import id.sc.proyek.databinding.FragmentAddAdminBinding
import id.sc.t7.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Fragment_AddAdmin : Fragment(

) {
    private lateinit var btnAddBeritaAdmin: Button
    private lateinit var txtHeadlineBeritaAdmin: EditText
    private lateinit var txtKeteranganAdmin: EditText
    private lateinit var txtSumbanganAdmin: EditText

    var onaddberitaListener:((key: String,berita: BeritaEntity)-> Unit)? = null
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.build(this.context)

        btnAddBeritaAdmin = view.findViewById(R.id.btnAddBeritaAdmin)
        txtHeadlineBeritaAdmin= view.findViewById(R.id.txtHeadlineBeritaAdmin)
        txtKeteranganAdmin= view.findViewById(R.id.txtKeteranganAdmin)
        txtSumbanganAdmin= view.findViewById(R.id.txtSumbanganAdmin)

        btnAddBeritaAdmin.setOnClickListener{
            var headline = txtHeadlineBeritaAdmin.text.toString()
            var keterangan = txtKeteranganAdmin.text.toString()
            var sumbangan = txtSumbanganAdmin.text.toString()

            if (headline==""||keterangan==""||sumbangan==""){
                Toast.makeText(view.context, "Field tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
            else{
                val newberita = BeritaEntity(headline,
                    keterangan,
                    sumbangan.toInt(),
                    "Admin"
                )
                onaddberitaListener?.invoke("berita",newberita)
            }
        }
        }






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_add_admin, container, false)



    }


}

