package com.ipvc.projetocm.viewModel

import androidx.lifecycle.*
import com.ipvc.projetocm.Model.Pagamento
import com.ipvc.projetocm.repository.PagamentoRepository
import kotlinx.coroutines.launch

class PagamentoViewModel(private val repository: PagamentoRepository) : ViewModel(){

    //sp q haja informação na bd, livedata vai manter a info atualizada
    val allPagamentos: LiveData<List<Pagamento>> = repository.allPagamentos.asLiveData()

    fun insert(pagamento: Pagamento) = viewModelScope.launch {
        repository.insert(pagamento)
    }
}

class PagamentoViewModelFactory(private val repository: PagamentoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PagamentoViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return PagamentoViewModel(repository) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}