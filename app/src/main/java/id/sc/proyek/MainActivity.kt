package id.sc.proyek

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import id.sc.proyek.databinding.ActivityMainBinding
import id.sc.t7.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase
    private val coroutine = CoroutineScope(Dispatchers.IO)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        //Inisialisasi pembuatan database
        db = AppDatabase.build(this)

        binding.txtGoToRegister.setOnClickListener(){
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener(){
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPass.text.toString()
            if (username==""||password=="")
            {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            }
            else if (username == "admin" && password == "admin"){
                val intent = Intent(this, HomeAdmin::class.java)
                startActivity(intent)
            }
            else{
                login(username,password)

            }
        }

    }
    fun login(username: String,password: String) {
        /*
        Untuk melakukan query DB, command2 query ditaruh di dalam scope coroutine.launch{}
        Artinya, command2 di dalam scope ini akan diexecute di luar main thread
         */
        coroutine.launch {
            if (db.UserDao.get(username,password) == null) {
                /*
                Di dalam scope thread IO, kita juga bisa mengexecute command di main thread
                dengan membuat scope runOnUiThread{}
                Warnin
                 */

                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Username not found", Toast.LENGTH_LONG)
                        .show()
                }

            }
            else{
                val user=db.UserDao.get(username,password)

                val intent = Intent(this@MainActivity, HomeUser::class.java)
                intent.putExtra("username", username)
                if (user != null) {
                    intent.putExtra("saldo", user.saldo)
                }

                startActivity(intent)
            }

        }
    }
}