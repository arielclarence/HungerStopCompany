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

class Fragment_TopUpUser : Fragment() {
    private lateinit var btnTopupUser: Button
    private lateinit var txtJumlahTopup: EditText

    var ontopupListener:((key: String,topup: Int)-> Unit)? = null
    private lateinit var db: AppDatabase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.build(this.context)

        btnTopupUser = view.findViewById(R.id.btnSumbanganUser)
        txtJumlahTopup= view.findViewById(R.id.txtJumlahSumbangan)

        btnTopupUser.setOnClickListener{
            var jumlah = txtJumlahTopup.text.toString()


            if (jumlah==""){
                Toast.makeText(view.context, "Field tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
            else if (jumlah.toInt()<1){
                Toast.makeText(view.context, "Topup harus diatas 0", Toast.LENGTH_SHORT).show()
            }
            else{
                ontopupListener?.invoke("topup",jumlah.toInt())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_up_user, container, false)
    }

}