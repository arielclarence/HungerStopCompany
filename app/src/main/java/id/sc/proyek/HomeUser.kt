package id.sc.proyek

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import id.sc.proyek.databinding.ActivityHomeAdminBinding
import id.sc.proyek.databinding.ActivityHomeUserBinding
import id.sc.t7.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeUser : AppCompatActivity() {
    lateinit var btnNavigasiUser: BottomNavigationView
    var username:String = ""
    var saldo:Int = 0
    private lateinit var binding: ActivityHomeUserBinding
    private lateinit var adapterberitausers: RVAdapterberitauser

    private lateinit var db: AppDatabase
    private val coroutine = CoroutineScope(Dispatchers.IO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = AppDatabase.build(this)
        binding = ActivityHomeUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        username = intent.getStringExtra("username")!!
        saldo = intent.getIntExtra("saldo",0)

        btnNavigasiUser = findViewById(R.id.bottomNavigationViewUser)

        swapToHome(username,saldo)
        btnNavigasiUser.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener() {
            when(it.itemId){
                R.id.Home_navigation_user->{
                    coroutine.launch {
                        saldo = db.UserDao.getwithusername(username)!!.saldo
                        swapToHome(username, saldo)
                    }
                    true
                }
                R.id.Topup_navigation_user->{
                    swapToTopup(username)
                    true
                }
                R.id.Add_navigation_user->{
                    swapToAdd(username)
                    true
                }
                else -> {
                    true
                }
            }
        })
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
                    Toast.makeText(this@HomeUser, "Headline Berita not unique", Toast.LENGTH_SHORT).show()
                }


            } else {

                db.BeritaDao.insert(berita)
                saldo = db.UserDao.getwithusername(username)!!.saldo
                swapToHome(username, saldo)
                runOnUiThread {
                    Toast.makeText(this@HomeUser,
                        "Berhasil Insert new berita",
                        Toast.LENGTH_SHORT).show()

                }


            }
        }
    }
    fun insertsumbangan( username: String, headline: String,sumbangan:Int) {

        coroutine.launch {
            val usernow=db.UserDao.getwithusername(username)
            if (usernow != null) {
                if (usernow.saldo < sumbangan) {

                    runOnUiThread {
                        Toast.makeText(this@HomeUser, "Saldo tidak mencukupi", Toast.LENGTH_SHORT).show()
                    }


                } else {
                    val newdonasi = DonasiEntity(
                        null,
                        username,
                        headline,
                        sumbangan
                    )
                    db.DonasiDao.insert(newdonasi)
                    db.UserDao.updatesaldo(usernow.saldo-sumbangan, username)
                    saldo = db.UserDao.getwithusername(username)!!.saldo
                    swapToHome(username, saldo)
                    runOnUiThread {
                        Toast.makeText(this@HomeUser,
                            "Berhasil Insert new donasi",
                            Toast.LENGTH_SHORT).show()

                    }


                }
            }
        }
    }
    fun Topupuser( topup: Int,username: String) {
        /*
        Untuk melakukan query DB, command2 query ditaruh di dalam scope coroutine.launch{}
        Artinya, command2 di dalam scope ini akan diexecute di luar main thread
         */

        coroutine.launch {

            var saldosblm= db.UserDao.getwithusername(username)!!.saldo
            db.UserDao.updatesaldo(saldosblm+topup, username)

                runOnUiThread {
                    Toast.makeText(this@HomeUser,
                        "Berhasil Topup",
                        Toast.LENGTH_SHORT).show()




            }
        }
    }
    private fun swapToHome(username:String,saldo:Int){
        variablesInit()
        var fragment = Fragment_HomeUser(username,saldo,adapterberitausers)
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.fragment_containeruser, fragment).setReorderingAllowed(true).commit()

    }

    private fun swapToSumbangan(headline: String,sisa:Int){
        coroutine.launch {
            saldo = db.UserDao.getwithusername(username)!!.saldo
            var beritayangdipilih= db.BeritaDao.getwithheadline(headline)

            var fragment = Fragment_UserSumbang(beritayangdipilih!!,saldo,sisa)

            val fragmentManager = supportFragmentManager.beginTransaction()
            fragmentManager.replace(R.id.fragment_containeruser, fragment).setReorderingAllowed(true).commit()
            fragment.onSumbanganListener= { key: String,Sumbangan:Int ->
                if(key == "sumbang"){

                    insertsumbangan(username,headline,Sumbangan)
                }

            }
        }
    }
    fun variablesInit(){
        var arrberita= ArrayList<BeritaEntity>()
        var arrdonasi= ArrayList<DonasiEntity>()
        coroutine.launch {

            var listberita= db.BeritaDao.fetch()!!
            for (berita in listberita){

                arrberita.add(berita)

            }
            var listdonasi= db.DonasiDao.fetch()!!
            for (donasi in listdonasi){

                arrdonasi.add(donasi)

            }

        }
        adapterberitausers = RVAdapterberitauser(arrdonasi,arrberita,{headline,sisa->
            swapToSumbangan(headline,sisa)})

    }
    private fun swapToAdd(username:String){

        var fragment = Fragment_AddUser(username)
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.fragment_containeruser, fragment).setReorderingAllowed(true).commit()
        fragment.onaddberitaListener= { key: String,berita:BeritaEntity ->
            if(key == "berita"){

                insert(berita)
            }

        }
    }
    private fun swapToTopup(username:String){

        var fragment = Fragment_TopUpUser()
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.fragment_containeruser, fragment).setReorderingAllowed(true).commit()
        fragment.ontopupListener= { key: String,topup:Int ->
            if(key == "topup"){

                Topupuser(topup,username)
            }

        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.keluar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.GoLogout){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        return true
    }
}