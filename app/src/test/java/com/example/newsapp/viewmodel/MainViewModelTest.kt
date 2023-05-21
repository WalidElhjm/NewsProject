package com.example.newsapp.viewmodel

import app.cash.turbine.test
import com.example.newsapp.util.MainCoroutineRule
import com.example.newsapp.common.DataState
import com.example.newsapp.data.model.NewsArticle
import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.use_case.GetNewsUseCase
import com.example.newsapp.presentation.news_list.NewsListState
import com.example.newsapp.presentation.news_list.NewsListViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val coroutineTestRule = MainCoroutineRule()

    lateinit var mainViewModel: NewsListViewModel

    @MockK(relaxed = true)
    lateinit var useCase: GetNewsUseCase
    @MockK(relaxed = true)
    lateinit var repository: NewsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mainViewModel = NewsListViewModel(
            useCase
        )
    }

    @Test
    fun getMainState() {
        assertThat(mainViewModel.state.value).isInstanceOf(NewsListState::class.java)
        assertThat(mainViewModel.state.value).isEqualTo(NewsListState())
    }


    @Test
    fun `getTopArticles should return an article`() = runTest {
        /** given */
        val response = NewsResponse(articles = listOf(NewsArticle(author = "OK")))
        coEvery { repository.getArticles() } answers {
            DataState.Success(response)
        }

        val results = arrayListOf<NewsListState>()

        val job = launch(UnconfinedTestDispatcher()) {
            mainViewModel.mainState.toList(results)
        }

        /** when */
        mainViewModel.getNews()

        /** then */
        assertThat(results[0].isLoading).isFalse()
        assertThat(results[1].isLoading).isTrue()
        assertThat(results[2].articles.count()).isEqualTo(1)
        assertThat(results[2].isLoading).isFalse()
        assertThat(results[2].error).isNull()

        coVerify(exactly = 1) { repository.getArticles() }
        job.cancel()
    }

    @Test
    fun `getTopArticles should should return an error`() = runTest {
        /** given */
        val error = UnknownHostException()
        coEvery { repository.getArticles() } returns DataState.Error(error)

        /** when */
        mainViewModel.getNews()

        /** then */
        mainViewModel.mainState.test {
            val data = awaitItem()
            assertThat(data.isLoading).isFalse()
            assertThat(data.articles).isEmpty()
            assertThat(data.error).isEqualTo(error)
        }
        coVerify(exactly = 1) { repository.getArticles() }
    }


}