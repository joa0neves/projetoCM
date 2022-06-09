package com.ipvc.projetocm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ipvc.projetocm.DAO.BilheteDao
import com.ipvc.projetocm.DAO.ReviewParqueDao
import com.ipvc.projetocm.DAO.UserDao
import com.ipvc.projetocm.Model.ReviewParque
import com.ipvc.projetocm.Model.User
import com.ipvc.projetocm.Model.Bilhete
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//marcar classe como database e indicar identidades
@Database(entities = [User::class, ReviewParque::class, Bilhete::class], version = 1, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {

    //indicar todos os DAO de todas as identidades
    abstract fun UserDao(): UserDao
    abstract fun BilheteDao(): BilheteDao
    abstract fun ReviewParqueDao(): ReviewParqueDao

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.UserDao ())
                }
            }
        }

        suspend fun populateDatabase(UserDao: UserDao) {
            // Delete all content here.
            UserDao.deleteAll()
            
            // Add sample Users.
            //var user = User(1, "Nuno Meira", "nunomeira@ipvc.pt","1234", 921922122)
            //UserDao.insert(user)

        }
    }

    //tudo que esta dentro do companion object pode ser usado fora da classe
    companion object{
        //unica instancia
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppRoomDatabase{
            return INSTANCE ?: synchronized(this){
                //criamos a db
                val instance = Room.databaseBuilder(
                    context.applicationContext, //contexto da db inteira
                    AppRoomDatabase::class.java, //bd
                    "App_database" //nome
                )
                    .addCallback(AppDatabaseCallback(scope))
                    .build()
                INSTANCE = instance //passar bd para instance
                instance //retorna a instance
            }
        }
    }
}

