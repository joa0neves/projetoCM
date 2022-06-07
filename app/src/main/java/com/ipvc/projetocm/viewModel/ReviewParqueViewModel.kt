package com.ipvc.projetocm.viewModel

import androidx.lifecycle.*
import com.ipvc.projetocm.Model.ReviewParque
import com.ipvc.projetocm.repository.ReviewParqueRepository
import kotlinx.coroutines.launch

class ReviewParqueViewModel(private val repository: ReviewParqueRepository) : ViewModel(){

    //sp q haja informação na bd, livedata vai manter a info atualizada
    val allReviews: LiveData<List<ReviewParque>> = repository.allReviews.asLiveData()

    fun insert(reviewParque: ReviewParque) = viewModelScope.launch {
        repository.insert(reviewParque)
    }
}

class ReviewParqueViewModelFactory(private val repository: ReviewParqueRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReviewParqueViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ReviewParqueViewModel(repository) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}