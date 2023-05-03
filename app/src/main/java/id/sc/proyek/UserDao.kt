package id.sc.proyek

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: UserEntity)

    @Update
    suspend fun update(user: UserEntity)

    @Delete
    suspend fun delete(user: UserEntity)

    @Query("DELETE FROM users where username = :username")
    suspend fun deleteQuery(username: String)

    @Query("UPDATE users SET saldo=:saldo WHERE username = :username")
    suspend fun updatesaldo(saldo: Int, username: String)

    @Query("SELECT * FROM users")
    suspend fun fetch():List<UserEntity>

    @Query("SELECT * FROM users where username = :username and password = :password")
    suspend fun get(username:String,password:String):UserEntity?

    @Query("SELECT * FROM users where username = :username")
    suspend fun getwithusername(username:String):UserEntity?

    @Query("SELECT saldo FROM users where username = :username")
    suspend fun getsaldo(username: String):Int?
}
