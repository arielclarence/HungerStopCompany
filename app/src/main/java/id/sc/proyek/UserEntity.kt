package id.sc.proyek

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey
    val username: String,
    val displayname: String,
    val password: String,
    val saldo: Int
) {

    override fun toString(): String {
        return super.toString()
    }


}



