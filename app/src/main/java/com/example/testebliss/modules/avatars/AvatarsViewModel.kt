package com.example.testebliss.modules.avatars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testebliss.CoreApplication.Companion.database
import com.example.testebliss.base.BaseViewModel
import com.example.testebliss.base.ViewState
import com.example.testebliss.models.RepoUserName
import com.example.testebliss.models.ResponseStatus
import com.example.testebliss.util.DispatcherProvider
import kotlinx.coroutines.launch

class AvatarsViewModel(private val dispatcherProvider: DispatcherProvider) :
    BaseViewModel(dispatcherProvider) {

    private val _reposLiveData = MutableLiveData<ViewState<List<RepoUserName>, ResponseStatus>>()
    val reposLiveData: LiveData<ViewState<List<RepoUserName>, ResponseStatus>> = _reposLiveData

    fun getAvatars() {
        scope.launch(dispatcherProvider.io) {
           val reposList = database?.reposDao()?.getAllRepos()
            reposList.takeIf { it!!.isNotEmpty() }?.let {
                _reposLiveData.postValue(ViewState(reposList, ResponseStatus.SUCCESS))
            }?: run {
                _reposLiveData.postValue(ViewState(status = ResponseStatus.EMPTY_LIST))
            }

        }
    }
}