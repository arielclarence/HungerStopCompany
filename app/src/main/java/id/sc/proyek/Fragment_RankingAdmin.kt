package id.sc.proyek

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Fragment_RankingAdmin(
    var adapterranking: RVAdapterRankinguser,

    ) : Fragment() {
    private lateinit var RVRankingUser: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RVRankingUser = view.findViewById(R.id.RVRankingUser)
        setLayoutManager(view)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ranking_admin, container, false)
    }
    private fun setLayoutManager(view: View){

        val verticalLayoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL,false)

        RVRankingUser.adapter = adapterranking
        RVRankingUser.layoutManager= verticalLayoutManager
    }


}