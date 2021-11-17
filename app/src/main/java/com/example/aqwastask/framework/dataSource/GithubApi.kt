package com.example.aqwastask.framework.dataSource

import com.example.aqwastask.business.entities.TrendingRepos
import com.example.aqwastask.framework.utils.Constants.Companion.REPOSITORIES
import retrofit2.http.GET

interface GithubApi {

    @GET(REPOSITORIES)
    suspend fun getTrendingRepos(): TrendingRepos
}