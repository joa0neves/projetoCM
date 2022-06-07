package com.ipvc.projetocm.viewModel

import androidx.lifecycle.*
import com.ipvc.projetocm.Model.User
import com.ipvc.projetocm.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel(){

    //sp q haja informação na bd, livedata vai manter a info atualizada
    val allUser: LiveData<List<User>> = repository.allPeople.asLiveData()

    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }
}

class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}