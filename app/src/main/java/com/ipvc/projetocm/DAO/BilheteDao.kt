package com.ipvc.projetocm.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy.IGNORE
import com.ipvc.projetocm.Model.Bilhete
import kotlinx.coroutines.flow.Flow

@Dao
interface BilheteDao {

    @Query("SELECT * FROM bilhete_table")
    fun getBilhetes(): Flow<List<Bilhete>>

    @Insert(onConflict = IGNORE)
    suspend fun insert(bilhete: Bilhete)

    @Query("DELETE FROM bilhete_table")
    suspend fun deleteAll()
}