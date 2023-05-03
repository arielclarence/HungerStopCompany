package id.sc.proyek

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import id.sc.proyek.databinding.ActivityRegisterBinding
import id.sc.t7.AppDatabase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    //Deklarasi variabel AppDatabase
    private lateinit var db: AppDatabase
    private val coroutine = CoroutineScope(Dispatchers.IO)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        db = AppDatabase.build(this)
        binding.btnLogin.setOnClickListener {
            val username = binding.txtUsernameRegis.text.toString()
            val displayname = binding.txtDisplayNameRegis.text.toString()
            val password = binding.txtPasswordRegis.text.toString()
            val connpassword = binding.connPassRegis.text.toString()

            if (username==""||password==""||displayname==""||connpassword=="")
            {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            }
            else{
                if (password!=connpassword)
                {
                    Toast.makeText(this, "Password dan confirm password harus sama", Toast.LENGTH_SHORT).show()
                }
                else{
                    val newuser = UserEntity(username,
                        displayname,
                        password,
                        0
                    )
                    insert(newuser)
                }


            }
        }
        binding.txtGoToLogin.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun insert(user: UserEntity) {
        /*
        Untuk melakukan query DB, command2 query ditaruh di dalam scope coroutine.launch{}
        Artinya, command2 di dalam scope ini akan diexecute di luar main thread
         */
        coroutine.launch {
            if (db.UserDao.getwithusername(user.username) != null) {
                /*
                Di dalam scope thread IO, kita juga bisa mengexecute command di main thread
                dengan membuat scope runOnUiThread{}
                Warnin
                 */
                runOnUiThread {
                    Toast.makeText(this@Register, "Username not unique", Toast.LENGTH_LONG)
                        .show()
                }
            } else {
                db.UserDao.insert(user)

                runOnUiThread {
                    Toast.makeText(this@Register, "Berhasil Insert new user", Toast.LENGTH_LONG).show()
                }
                finish()
            }
        }
    }

}