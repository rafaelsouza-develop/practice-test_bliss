package com.example.testebliss.modules.googlerepos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testebliss.base.BaseViewModel
import com.example.testebliss.base.ViewState
import com.example.testebliss.data.network.Result
import com.example.testebliss.models.GoogleRepos
import com.example.testebliss.models.ResponseStatus
import com.example.testebliss.repository.googlerepos.GoogleReposRepository
import com.example.testebliss.util.DispatcherProvider
import kotlinx.coroutines.launch

class GoogleReposViewModel(
    private val dispatcherProvider: DispatcherProvider,
    private val googleReposRepository: GoogleReposRepository
) : BaseViewModel(dispatcherProvider) {
    var page = 1
    private val _googleReposLiveData =
        MutableLiveData<ViewState<List<GoogleRepos>, ResponseStatus>>()
    val googleReposLiveData: LiveData<ViewState<List<GoogleRepos>, ResponseStatus>> =
        _googleReposLiveData

    fun getGoogleRepos(googleRepo: String) {
        scope.launch(dispatcherProvider.ui) {
            _googleReposLiveData.postValue(ViewState(status = ResponseStatus.LOADING))
            when (val response = googleReposRepository.getGoogleRepos(googleRepo, page, 10)) {
                is Result.Success -> {
                    _googleReposLiveData.postValue(ViewState(status = ResponseStatus.UNLOADING))
                    response.data.takeIf { it.isNotEmpty() }?.let {
                        page++
                        _googleReposLiveData.postValue(ViewState(it, ResponseStatus.SUCCESS))
                    }
                }
                is Result.Failure -> {
                    _googleReposLiveData.postValue(ViewState(status = ResponseStatus.UNLOADING))
                }
            }
        }
    }

}