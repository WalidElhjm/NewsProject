package com.example.newsapp.repository


import com.bikcodeh.newsapp.util.MainCoroutineRule
import com.example.newsapp.common.DataState
import com.example.newsapp.data.model.NewsArticle
import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.repository.NewsRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class TopNewsRepositoryImplTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    lateinit var topNewsRepository: NewsRepository

    @RelaxedMockK
    lateinit var newsService: NewsApi

    private val error = Exception("exception")

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        topNewsRepository = NewsRepositoryImpl(
            newsService
        )
    }

    @Test
    fun getArticles() = runTest {
        /** Given */
        val expected = NewsResponse(
            status = "OK",
            totalResults = 1,
            articles = listOf(NewsArticle(author = "test"))
        )
        coEvery { newsService.getTopArticles("us") } answers { expected }

        /** When */
        val result = topNewsRepository.getArticles()

        /** Then */
        assertThat(result).isInstanceOf(DataState.Success::class.java)
        assertThat((result as DataState.Success).data?.status).isEqualTo("OK")
        assertThat(result.data?.articles?.count()).isEqualTo(1)
        assertThat((result).data?.articles?.first()?.author).isEqualTo("test")
        coVerify(exactly = 1) { newsService.getTopArticles("us") }
    }

    @Test
    fun `getArticles should return a error result catching exception`() = runTest {
        /** Given */
        coEvery { newsService.getTopArticles("us") } throws error

        /** When */
        val result = topNewsRepository.getArticles()

        /** Then */
        assertThat(result).isInstanceOf(DataState.Error::class.java)
        assertThat((result as DataState.Error).message).isEqualTo("exception")
        coVerify(exactly = 1) { newsService.getTopArticles("us") }
    }

    @Test
    fun `getArticles should return a error result catching unknown host exception`() = runTest {
        val error = UnknownHostException("without_host")
        /** Given */
        coEvery { newsService.getTopArticles("us") } throws error

        /** When */
        val result = topNewsRepository.getArticles()

        /** Then */
        assertThat(result).isInstanceOf(DataState.Error::class.java)
        assertThat((result as DataState.Error).message).isInstanceOf(UnknownHostException::class.java)
        assertThat((result).message).isEqualTo("without_host")
        coVerify(exactly = 1) { newsService.getTopArticles("us") }
    }

}