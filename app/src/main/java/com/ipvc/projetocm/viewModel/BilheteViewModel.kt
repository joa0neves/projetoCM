package com.ipvc.projetocm.viewModel

import androidx.lifecycle.*
import com.ipvc.projetocm.Model.Bilhete
import com.ipvc.projetocm.repository.BilheteRepository
import kotlinx.coroutines.launch

class BilheteViewModel(private val repository: BilheteRepository) : ViewModel(){

    //sp q haja informação na bd, livedata vai manter a info atualizada
    val allBilhete: LiveData<List<Bilhete>> = repository.allBilhetes.asLiveData()

    fun insert(bilhete: Bilhete) = viewModelScope.launch {
        repository.insert(bilhete)
    }
}

class BilheteViewModelFactory(private val repository: BilheteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BilheteViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return BilheteViewModel(repository) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}