package id.sc.proyek

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Fragment_ListAdmin(
    var adapterlist: RVAdapterListuser,
) : Fragment() {


    private lateinit var RVUser: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        RVUser = view.findViewById(R.id.RVUser)
        setLayoutManager(view)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_admin, container, false)
    }
    private fun setLayoutManager(view: View){

        val verticalLayoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL,false)

        RVUser.adapter = adapterlist
        RVUser.layoutManager= verticalLayoutManager
    }

}