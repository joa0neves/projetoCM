package com.ipvc.projetocm.repository

import androidx.annotation.WorkerThread
import com.ipvc.projetocm.DAO.ReviewParqueDao
import com.ipvc.projetocm.Model.ReviewParque
import kotlinx.coroutines.flow.Flow

class ReviewParqueRepository(private val reviewParqueDao: ReviewParqueDao) {

    val allReviews: Flow<List<ReviewParque>> = reviewParqueDao.getReviews()

    @Suppress("RedudantSuspendModifier")
    @WorkerThread
    suspend fun insert(reviewParque: ReviewParque) {
        reviewParqueDao.insert(reviewParque)
    }
}