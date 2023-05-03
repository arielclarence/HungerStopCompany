package id.sc.proyek

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import id.sc.t7.AppDatabase

class Fragment_AddUser(
    var username:String,
) : Fragment() {

    private lateinit var btnAddBeritaUser: Button
    private lateinit var txtHeadlineBeritaUser: EditText
    private lateinit var txtKeteranganUser: EditText
    private lateinit var txtSumbanganUser: EditText

    var onaddberitaListener:((key: String,berita: BeritaEntity)-> Unit)? = null
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.build(this.context)

        btnAddBeritaUser = view.findViewById(R.id.btnAddBeritaUser)
        txtHeadlineBeritaUser= view.findViewById(R.id.txtHeadlineBeritaUser)
        txtKeteranganUser= view.findViewById(R.id.txtKeteranganUser)
        txtSumbanganUser= view.findViewById(R.id.txtSumbanganUser)
        btnAddBeritaUser.setOnClickListener{
            var headline = txtHeadlineBeritaUser.text.toString()
            var keterangan = txtKeteranganUser.text.toString()
            var sumbangan = txtSumbanganUser.text.toString()

            if (headline==""||keterangan==""||sumbangan==""){
                Toast.makeText(view.context, "Field tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
            else{
                val newberita = BeritaEntity(headline,
                    keterangan,
                    sumbangan.toInt(),
                    username
                )
                onaddberitaListener?.invoke("berita",newberita)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_user, container, false)
    }

}