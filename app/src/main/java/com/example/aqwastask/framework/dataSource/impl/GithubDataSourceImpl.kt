package com.example.aqwastask.framework.dataSource.impl

import com.example.aqwastask.business.entities.TrendingRepos
import com.example.aqwastask.framework.dataSource.GithubApi
import com.example.aqwastask.framework.dataSource.abstraction.GithubDataSource
import javax.inject.Inject

class GithubDataSourceImpl @Inject constructor(private val githubApi: GithubApi) :
    GithubDataSource {

    override suspend fun getTrendingRepos(): TrendingRepos = githubApi.getTrendingRepos()
}