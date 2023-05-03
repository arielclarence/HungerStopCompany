package id.sc.proyek

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "donasi")
data class DonasiEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val username: String,
    val headline: String,
    val sumbangan: Int,

    ){
}
