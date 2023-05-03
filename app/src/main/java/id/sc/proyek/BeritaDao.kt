package id.sc.proyek

import androidx.room.*
/*
DAO ini interface yang jadi perantara kotlin dengan database.
Isinya method2 yang digunakan untuk melakukan query ke database
Untuk command basic kyk insert, update, dan delete bisa pake annotation
@Insert, @Update, dan @Delete dengan objek yang mau diinsert/update/delete yang dipassing sebagai
parameternya.

Kalo query2 yang bersifat lebih spesifik, bisa pakai @Query dengan isi querynya sebagai
parameter functionnya
 */
@Dao
interface BeritaDao {
    @Insert
    suspend fun insert(berita:BeritaEntity)

    @Update
    suspend fun update(berita:BeritaEntity)

    @Delete
    suspend fun delete(berita:BeritaEntity)

    @Query("DELETE FROM berita where headline = :headline")
    suspend fun deleteQuery(headline:String)

    @Query("SELECT * FROM berita")
    suspend fun fetch():List<BeritaEntity>
    @Query("SELECT * FROM berita where headline = :headline")
    suspend fun getwithheadline(headline:String):BeritaEntity?

    @Query("SELECT * FROM berita where headline = :headline and keterangan = :keterangan")
    suspend fun get(headline:String,keterangan:String):BeritaEntity?

    @Query("SELECT * FROM berita where jumlahsumbangan = 0")
    suspend fun getwithsumbangan():BeritaEntity?
}