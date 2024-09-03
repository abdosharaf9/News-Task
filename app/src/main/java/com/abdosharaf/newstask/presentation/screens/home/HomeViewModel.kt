package com.abdosharaf.newstask.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdosharaf.newstask.core.utils.onResponse
import com.abdosharaf.newstask.domain.usecases.GetTopHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getTopHeadlines()
    }

    fun handleActions(action: HomeAction) {
        when (action) {
            is HomeAction.OnCategoryClicked -> {
                _uiState.update { it.copy(selectedCategory = action.category) }
                getTopHeadlines()
            }
        }
    }

    private fun getTopHeadlines() {
        viewModelScope.launch(Dispatchers.IO) {
            getTopHeadlinesUseCase(_uiState.value.selectedCategory.id).onResponse(
                onLoading = {
                    _uiState.update {
                        it.copy(
                            isLoading = true,
                            isNetworkError = false,
                            resultCount = 0,
                            articles = emptyList()
                        )
                    }
                },
                onSuccess = { response ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            articles = response.articles,
                            resultCount = response.totalResults
                        )
                    }
                },
                onFailure = {
                    _uiState.update { it.copy(isError = true, isLoading = false) }
                },
                onNetworkError = {
                    _uiState.update { it.copy(isNetworkError = true, isLoading = false) }
                }
            )
        }
    }
}