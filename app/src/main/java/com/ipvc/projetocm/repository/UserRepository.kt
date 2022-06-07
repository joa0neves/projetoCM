package com.ipvc.projetocm.repository

import androidx.annotation.WorkerThread
import com.ipvc.projetocm.DAO.UserDao
import com.ipvc.projetocm.Model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val allPeople: Flow<List<User>> = userDao.getOrderedUser()

    @Suppress("RedudantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User) {
        userDao.insert(user)
    }
}