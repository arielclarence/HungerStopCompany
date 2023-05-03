package id.sc.t7

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.sc.proyek.*


@Database(entities = [
    UserEntity::class,
    DonasiEntity::class,
    BeritaEntity::class
], version=1)
abstract class AppDatabase : RoomDatabase(){
    //Deklarasi variabel DAO untuk class UserEntity
    abstract val UserDao: UserDao
    abstract val DonasiDao: DonasiDao
    abstract val BeritaDao: BeritaDao

    //Line di bawah ini mostly hafalan
    companion object {
        private var _database: AppDatabase? = null

        fun build(context:Context?): AppDatabase {
            if(_database == null){
                //
                _database = Room.databaseBuilder(context!!,AppDatabase::class.java,"proyek_databasebaru6").build()

            }
            return _database!!
        }
    }
}