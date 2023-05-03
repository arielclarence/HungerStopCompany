package id.sc.proyek

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import id.sc.proyek.databinding.ActivityHomeAdminBinding
import id.sc.proyek.databinding.ActivityMainBinding
import id.sc.t7.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeAdmin : AppCompatActivity() {
    private lateinit var binding: ActivityHomeAdminBinding
    private lateinit var adapterberitas: RVAdapterberita
    private lateinit var adapterlistuser: RVAdapterListuser
    private lateinit var adapterdonasiuser: RVAdapterListDetailDonasi
    private lateinit var adapterrankinguser: RVAdapterRankinguser

    private lateinit var db: AppDatabase
    private val coroutine = CoroutineScope(Dispatchers.IO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = AppDatabase.build(this)
        binding = ActivityHomeAdminBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        swapToHome()
        binding.bottomNavigationViewUser.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener() {
            when(it.itemId){
                R.id.Home_navigation_menu->{
                    swapToHome()
                    true
                }
                R.id.List_navigation_menu->{
                    swapToListUser()
                    true
                }
                R.id.Add_navigation_menu->{
                    swapToAdd()
                    true
                }
                R.id.Ranking_navigation_menu->{
                    swapToRankUser()
                    true
                }
                else -> {
                    true
                }
            }
        })
    }

    private fun swapToHome(){
        variablesInit()
        var fragment = Fragment_HomeAdmin(adapterberitas)
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.fragment_containeradmin, fragment).setReorderingAllowed(true).commit()
//        true
    }
    private fun swapToListUser(){
        variablesInit()
        var fragment = Fragment_ListAdmin(adapterlistuser)
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.fragment_containeradmin, fragment).setReorderingAllowed(true).commit()
//        true
    }
    private fun swapToRankUser(){
        variablesInit()
        coroutine.launch {
            var arrRank= ArrayList<Rank>()

            var listuser= db.UserDao.fetch()
            var listdonasi= db.DonasiDao.fetch()
            var arrdonasiall= ArrayList<DonasiEntity>()
            var arruserall= ArrayList<UserEntity>()

            for (user in listuser!!){

                arruserall.add(user)

            }
            for (donasi in listdonasi!!){

                arrdonasiall.add(donasi)

            }
            for (user in arruserall){
                var totaldonasiuser=0
                for (donasi in arrdonasiall){

                    if (user.username==donasi.username){
                        totaldonasiuser+=donasi.sumbangan
                    }

                }
                val newrank = Rank(user.username,
                    totaldonasiuser
                )
                arrRank.add(newrank)
            }
            arrRank.sortByDescending {Rank ->  Rank.totaldonasi}
            if (arrRank.size>5){
                arrRank= arrRank.take(5) as ArrayList<Rank>
            }

            adapterrankinguser=RVAdapterRankinguser(arrRank)

            var fragment = Fragment_RankingAdmin(adapterrankinguser)
            val fragmentManager = supportFragmentManager.beginTransaction()
            fragmentManager.replace(R.id.fragment_containeradmin, fragment).setReorderingAllowed(true).commit()
        }

    }
    fun insert( berita: BeritaEntity) {
        /*
        Untuk melakukan query DB, command2 query ditaruh di dalam scope coroutine.launch{}
        Artinya, command2 di dalam scope ini akan diexecute di luar main thread
         */

        coroutine.launch {

            if (db.BeritaDao.getwithheadline(berita.headline) != null) {
                /*
                Di dalam scope thread IO, kita juga bisa mengexecute command di main thread
                dengan membuat scope runOnUiThread{}
                Warnin
                 */
                runOnUiThread {
                    Toast.makeText(this@HomeAdmin, "Headline Berita not unique", Toast.LENGTH_SHORT).show()
                }


            } else {
                db.BeritaDao.insert(berita)
                runOnUiThread {
                    Toast.makeText(this@HomeAdmin,
                        "Berhasil Insert new berita",
                        Toast.LENGTH_SHORT).show()

                }


            }
        }
    }
    private fun swapToAdd(){

        var fragment = Fragment_AddAdmin()
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.fragment_containeradmin, fragment).setReorderingAllowed(true).commit()
        fragment.onaddberitaListener= { key: String,berita:BeritaEntity ->
            if(key == "berita"){

                insert(berita)
            }

        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.keluar, menu)
        return super.onCreateOptionsMenu(menu)
    }
    fun variablesInit(){
        var arrberita= ArrayList<BeritaEntity>()
        var arruser= ArrayList<UserEntity>()
        var arrdonasi= ArrayList<DonasiEntity>()

        coroutine.launch {

            var listberita= db.BeritaDao.fetch()!!
            for (berita in listberita){

                arrberita.add(berita)

            }

            var listuser= db.UserDao.fetch()!!
            for (user in listuser){

                arruser.add(user)

            }
            var listdonasi= db.DonasiDao.fetch()!!
            for (donasi in listdonasi){

                arrdonasi.add(donasi)

            }

        }

        adapterberitas = RVAdapterberita(arrdonasi,arrberita,{headline->
            swapToDetailSumbangan(headline)})
        adapterlistuser = RVAdapterListuser(arruser)


    }
    private fun swapToDetailSumbangan(headline: String){
        coroutine.launch {
            var arrdonasiberitayangdipilih= ArrayList<DonasiEntity>()

            var listdonasiberitayangdipilih= db.DonasiDao.getwithheadline(headline)

            for (donasi in listdonasiberitayangdipilih!!){

                arrdonasiberitayangdipilih.add(donasi)

            }
            adapterdonasiuser=RVAdapterListDetailDonasi(arrdonasiberitayangdipilih)
            var fragment = Fragment_DetailDonasiBerita(headline,adapterdonasiuser)

            val fragmentManager = supportFragmentManager.beginTransaction()
            fragmentManager.replace(R.id.fragment_containeradmin, fragment).setReorderingAllowed(true).commit()

        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.GoLogout){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        return true
    }
}