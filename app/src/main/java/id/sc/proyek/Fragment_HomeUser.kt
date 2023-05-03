package id.sc.proyek

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Fragment_HomeUser(


    var username:String,
    var saldo:Int,
    var adapterberita: RVAdapterberitauser,
) : Fragment() {
    private lateinit var txtNamaUser: TextView
    private lateinit var txtSaldoUser: TextView

    private lateinit var RVberitauser: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtNamaUser = view.findViewById(R.id.txtNamaUser)
        txtSaldoUser = view.findViewById(R.id.txtSaldoUser)

        RVberitauser = view.findViewById(R.id.RVberitauser)
        txtNamaUser.setText("Welcome, "+username)
        txtSaldoUser.setText("Saldo: Rp"+saldo)

        setLayoutManager(view)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_user, container, false)
    }
    private fun setLayoutManager(view: View){

        val verticalLayoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL,false)

        RVberitauser.adapter = adapterberita
        RVberitauser.layoutManager= verticalLayoutManager
    }
}