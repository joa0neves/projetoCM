package com.ipvc.projetocm.repository

import androidx.annotation.WorkerThread
import com.ipvc.projetocm.DAO.PagamentoDao
import com.ipvc.projetocm.Model.Pagamento
import kotlinx.coroutines.flow.Flow

class PagamentoRepository(private val pagamentoDao: PagamentoDao) {

    val allPagamentos: Flow<List<Pagamento>> = pagamentoDao.getPagamentos()

    @Suppress("RedudantSuspendModifier")
    @WorkerThread
    suspend fun insert(pagamento: Pagamento) {
        pagamentoDao.insert(pagamento)
    }
}