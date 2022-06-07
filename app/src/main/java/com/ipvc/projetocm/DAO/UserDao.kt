package com.ipvc.projetocm.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy.IGNORE
import com.ipvc.projetocm.Model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table ORDER BY name")
    fun getOrderedUser(): Flow<List<User>>

    @Insert(onConflict = IGNORE)
    suspend fun insert(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()
}