package id.sc.proyek

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
Entity ini ibarat sebuah tabel dalam database. Sebuah entity wajib ada annotation @Entity nya
disertai dengan nama table

Primary key sebuah tabel ditentukan dengan menaruh sebuah @PrimaryKey di ATAS sebuah variabel
Pada kasus file ini, username menjadi sebuah primary keynya.

Variabel lain akan dianggap sebuah column dalam database dengan nama variabel itu sendiri sebagai
nama column
 */

@Entity(tableName = "berita")
data class BeritaEntity(
    @PrimaryKey
    val headline: String,
    val keterangan: String,
    val jumlahsumbangan:  Int,
    val pembuat:  String
){
    override fun toString(): String {
        return ""
    }
}
