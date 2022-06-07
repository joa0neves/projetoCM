package com.ipvc.projetocm.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy.IGNORE
import com.ipvc.projetocm.Model.Pagamento
import kotlinx.coroutines.flow.Flow

@Dao
interface PagamentoDao {

    @Query("SELECT * FROM pagamento_table")
    fun getPagamentos(): Flow<List<Pagamento>>

    @Insert(onConflict = IGNORE)
    suspend fun insert(pagamento: Pagamento)

    @Query("DELETE FROM pagamento_table")
    suspend fun deleteAll()
}