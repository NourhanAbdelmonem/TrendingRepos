package com.example.aqwastask.business.usecases.abstraction

import com.example.aqwastask.business.entities.TrendingRepos
import kotlinx.coroutines.flow.Flow

interface GithubUseCase {

    suspend fun getTrendingRepos(): Flow<TrendingRepos>
}