package com.example.newsapp.presentation.news_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.common.DataState
import com.example.newsapp.data.model.NewsArticle
import com.example.newsapp.domain.use_case.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(NewsListState())
    val state: State<NewsListState> = _state
    private val _selectedNews: MutableStateFlow<NewsArticle?> = MutableStateFlow(null)
    val selectedNews: StateFlow<NewsArticle?> = _selectedNews.asStateFlow()

    private val _mainState = MutableStateFlow(NewsListState())
    val mainState: StateFlow<NewsListState>
        get() = _mainState.asStateFlow()
    init {
        getNews()
    }

    fun getSelectedArticle(index: Int?) {

        getNewsUseCase().onEach {
            _selectedNews.value = _state.value.articles[index!!]

        }.launchIn(viewModelScope)

    }

    fun getNews() {
        // for states
        getNewsUseCase().onEach { result ->
            when (result) {

                is DataState.Success -> {
                    withContext(Dispatchers.Main) {
                        _state.value = result.data?.articles?.let { NewsListState(articles = it) }!!
                    }
                }

                is DataState.Error -> {
                    withContext(Dispatchers.Main) {
                        _state.value = NewsListState(isLoading = false, error = result.message)
                    }
                }
                is DataState.Loading -> {
                    _state.value = NewsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }


}