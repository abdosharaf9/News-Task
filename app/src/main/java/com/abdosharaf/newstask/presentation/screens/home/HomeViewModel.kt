package com.abdosharaf.newstask.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdosharaf.newstask.core.utils.ResultWrapper
import com.abdosharaf.newstask.domain.usecases.GetTopHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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
            _uiState.update { it.copy(isLoading = true, isNetworkError = false) }
            delay(300)

            getTopHeadlinesUseCase(_uiState.value.selectedCategory.id).collectLatest { result ->
                when (result) {
                    is ResultWrapper.Loading -> {
                        _uiState.update {
                            it.copy(
                                articles = result.data?.articles ?: emptyList(),
                                resultCount = result.data?.totalResults ?: 0
                            )
                        }
                    }

                    is ResultWrapper.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                articles = result.data?.articles ?: emptyList(),
                                resultCount = result.data?.totalResults ?: 0
                            )
                        }
                    }

                    is ResultWrapper.Failure -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                articles = result.data?.articles ?: emptyList(),
                                resultCount = result.data?.totalResults ?: 0
                            )
                        }
                    }

                    is ResultWrapper.NoInternet -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                articles = result.data?.articles ?: emptyList(),
                                resultCount = result.data?.totalResults ?: 0,
                                isNetworkError = true
                            )
                        }
                    }
                }
            }
        }
    }
}