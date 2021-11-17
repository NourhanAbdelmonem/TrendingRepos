package com.example.aqwastask.business.usecases.impl

import com.example.aqwastask.business.entities.TrendingRepos
import com.example.aqwastask.business.repositories.abstraction.GithubRepository
import com.example.aqwastask.business.usecases.abstraction.GithubUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubUseCaseImpl @Inject constructor(private val githubRepository: GithubRepository) :
    GithubUseCase {

    override suspend fun getTrendingRepos(): Flow<TrendingRepos> =
        githubRepository.getTrendingRepos()
}