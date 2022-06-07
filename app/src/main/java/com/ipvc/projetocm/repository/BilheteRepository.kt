package com.ipvc.projetocm.repository

import androidx.annotation.WorkerThread
import com.ipvc.projetocm.DAO.BilheteDao
import com.ipvc.projetocm.Model.Bilhete
import kotlinx.coroutines.flow.Flow

class BilheteRepository(private val bilheteDao: BilheteDao) {

    val allBilhetes: Flow<List<Bilhete>> = bilheteDao.getBilhetes()

    @Suppress("RedudantSuspendModifier")
    @WorkerThread
    suspend fun insert(bilhete: Bilhete) {
        bilheteDao.insert(bilhete)
    }
}