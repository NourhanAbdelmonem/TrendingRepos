package com.example.aqwastask

import com.example.aqwastask.business.entities.Item
import com.example.aqwastask.business.entities.TrendingRepos
import com.example.aqwastask.business.repositories.abstraction.GithubRepository
import com.example.aqwastask.business.repositories.impl.GithubRepositoryImpl
import com.example.aqwastask.framework.dataSource.abstraction.GithubDataSource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.exceptions.base.MockitoException


class TrendingReposTest {

    private lateinit var repository: GithubRepository

    private var githubDataSource: GithubDataSource = object : GithubDataSource {
        override suspend fun getTrendingRepos(): TrendingRepos = trendingRepos
    }
    var throwableDataSource: GithubDataSource = object : GithubDataSource {
        override suspend fun getTrendingRepos(): TrendingRepos = throw throwableResult
    }

    @Test
    fun `getGithubRepos() with success response then return response`() {

        runBlocking {
            repository = GithubRepositoryImpl(githubDataSource)
            val response = repository.getTrendingRepos()

            var success: TrendingRepos? = null
            var error: Throwable? = null

            response.catch {
                error = it
            }.collect {
                success = it
            }

            Assert.assertNull(error)
            Assert.assertNotNull(success)
            assertEquals(success!!, trendingRepos)
            assertEquals(success!!.msg, trendingRepos.msg)
        }
    }


    @Test
    fun `getGithubRepos() with failed throwable then return throwable`() {
        runBlocking {
            repository = GithubRepositoryImpl(throwableDataSource)
            val response = repository.getTrendingRepos()

            var success: TrendingRepos? = null
            var error: Throwable? = null
            response.catch {
                error = it
            }.collect {
                success = it
            }

            Assert.assertNull(success)
            Assert.assertNotNull(error)
            assertEquals(error?.message!!, throwableResult.message!!)
        }
    }

    private val trendingRepos =
        TrendingRepos(
            count = 2,
            listOf(
                Item(
                    addedStars = "1",
                    listOf<String>(),
                    desc = "desc",
                    forks = "forks",
                    lang = "lang",
                    repo = "repo",
                    repoLink = "repo link",
                    stars = "stars",
                    isExpanded = false
                )
            ),
            msg = "success"
        )
    private val throwableResult = MockitoException("Unknown Error")

}