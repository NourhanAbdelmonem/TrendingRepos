package com.example.aqwastask.framework.dataSource.abstraction

import com.example.aqwastask.business.entities.TrendingRepos

interface GithubDataSource {
    suspend fun getTrendingRepos(): TrendingRepos
}