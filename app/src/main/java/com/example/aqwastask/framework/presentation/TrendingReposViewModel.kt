package com.example.aqwastask.framework.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aqwastask.business.entities.TrendingRepos
import com.example.aqwastask.business.usecases.abstraction.GithubUseCase
import com.example.aqwastask.business.utils.DataState
import com.example.aqwastask.business.utils.DataState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingReposViewModel @Inject constructor(private val githubUseCase: GithubUseCase) :
    ViewModel() {

    private val _reposDataState: MutableLiveData<DataState<TrendingRepos>> = MutableLiveData()
    val reposDataState: LiveData<DataState<TrendingRepos>> get() = _reposDataState


    init {
        getTrendingRepos()
    }


    fun getTrendingRepos() {
        viewModelScope.launch {
            githubUseCase.getTrendingRepos()
                .onStart { _reposDataState.value = Loading(true) }
                .catch {
                    _reposDataState.value = Loading(false)
                    _reposDataState.value = Failure(it)
                }.collect {
                    _reposDataState.value = Loading(false)
                    _reposDataState.value = Success(it)
                }
        }
    }

}