package com.ipvc.projetocm.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy.IGNORE
import com.ipvc.projetocm.Model.ReviewParque
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewParqueDao {

    @Query("SELECT * FROM reviewParque_table")
    fun getReviews(): Flow<List<ReviewParque>>

    @Insert(onConflict = IGNORE)
    suspend fun insert(reviewParque: ReviewParque)

    @Query("DELETE FROM reviewParque_table")
    suspend fun deleteAll()
}