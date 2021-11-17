package com.example.aqwastask.business.repositories.abstraction

import com.example.aqwastask.business.entities.TrendingRepos
import kotlinx.coroutines.flow.Flow

interface GithubRepository {

    suspend fun getTrendingRepos(): Flow<TrendingRepos>
}