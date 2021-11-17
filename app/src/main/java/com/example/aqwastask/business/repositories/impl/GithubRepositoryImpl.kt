package com.example.aqwastask.business.repositories.impl

import com.example.aqwastask.business.entities.TrendingRepos
import com.example.aqwastask.business.repositories.abstraction.GithubRepository
import com.example.aqwastask.framework.dataSource.abstraction.GithubDataSource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(private val githubDataSource: GithubDataSource) :
    GithubRepository {

    override suspend fun getTrendingRepos(): Flow<TrendingRepos> = flow {
        emit(githubDataSource.getTrendingRepos())
    }.flowOn(IO)
}