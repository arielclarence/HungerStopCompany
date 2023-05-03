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
interface DonasiDao {
    @Insert
    suspend fun insert(donasi:DonasiEntity)

    @Update
    suspend fun update(donasi:DonasiEntity)

    @Delete
    suspend fun delete(donasi:DonasiEntity)

    @Query("DELETE FROM donasi where username = :username and headline= :headline")
    suspend fun deleteQuery(username:String,headline:String)

    @Query("SELECT * FROM donasi")
    suspend fun fetch():List<DonasiEntity>

    @Query("SELECT * FROM donasi where username = :username and headline = :headline")
    suspend fun get(username:String,headline:String):DonasiEntity?

    @Query("SELECT * FROM donasi where username = :username")
    suspend fun getwithusername(username:String):List<DonasiEntity>?

    @Query("SELECT * FROM donasi where headline = :headline")
    suspend fun getwithheadline(headline:String):List<DonasiEntity>?
}