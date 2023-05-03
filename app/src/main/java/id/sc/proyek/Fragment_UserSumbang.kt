package id.sc.proyek

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import id.sc.t7.AppDatabase

class Fragment_UserSumbang(
    var beritayangdipilih:BeritaEntity,
    var saldo:Int,
    var sisa:Int
) : Fragment() {
    private lateinit var btnSumbangUser: Button
    private lateinit var txtJumlahSumbangan: EditText
    private lateinit var txtSaldoUserSumbang: TextView
    private lateinit var txtHeadlineBeritaSumbang: TextView
    private lateinit var txtKeteranganBeritaSumbang: TextView
    private lateinit var txtSisaDonasi: TextView

    var onSumbanganListener:((key: String,sumbang: Int)-> Unit)? = null
    private lateinit var db: AppDatabase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.build(this.context)

        btnSumbangUser = view.findViewById(R.id.btnSumbanganUser)
        txtJumlahSumbangan= view.findViewById(R.id.txtJumlahSumbangan)
        txtSaldoUserSumbang= view.findViewById(R.id.txtSaldoUserSumbang)
        txtHeadlineBeritaSumbang= view.findViewById(R.id.txtHeadlineBeritaSumbang)
        txtKeteranganBeritaSumbang= view.findViewById(R.id.txtKeteranganBeritaSumbang)
        txtSisaDonasi= view.findViewById(R.id.txtSisaDonasi)

        txtSaldoUserSumbang.setText("Saldo: Rp"+saldo)
        txtSisaDonasi.setText("Sisa Donasi Menuju Goal: Rp"+sisa)

        txtHeadlineBeritaSumbang.setText("Headline: "+beritayangdipilih.headline)
        txtKeteranganBeritaSumbang.setText("Keterangan: "+beritayangdipilih.keterangan)

        btnSumbangUser.setOnClickListener{
            var jumlah = txtJumlahSumbangan.text.toString()
            if (jumlah==""){
                Toast.makeText(view.context, "Field tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
            else if (jumlah.toInt()<1){
                Toast.makeText(view.context, "Sumbangan harus diatas 0", Toast.LENGTH_SHORT).show()
            }
            else if (sisa<jumlah.toInt()){
                Toast.makeText(view.context, "Sumbangan tidak boleh melebihi goal", Toast.LENGTH_SHORT).show()
            }
            else{
                onSumbanganListener?.invoke("sumbang",jumlah.toInt())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sumbang_user, container, false)
    }

}