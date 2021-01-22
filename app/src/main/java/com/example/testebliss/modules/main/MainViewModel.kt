package com.example.testebliss.modules.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testebliss.base.BaseViewModel
import com.example.testebliss.base.ViewState
import com.example.testebliss.data.network.Result
import com.example.testebliss.models.Emoji
import com.example.testebliss.models.RepoUserName
import com.example.testebliss.models.ResponseStatus
import com.example.testebliss.repository.emoji.EmojiRepository
import com.example.testebliss.repository.reposusername.RepoUserNameRepository
import com.example.testebliss.util.DispatcherProvider
import kotlinx.coroutines.launch

class MainViewModel(
    private val dispatcherProvider: DispatcherProvider,
    private val emojiRepository: EmojiRepository,
    private val repoUserNameRepository: RepoUserNameRepository
) :
    BaseViewModel(dispatcherProvider) {

    private val _emojisLiveData = MutableLiveData<ViewState<List<Emoji>, ResponseStatus>>()
    val emojisLiveData: LiveData<ViewState<List<Emoji>, ResponseStatus>> = _emojisLiveData

    private val _repoUserLiveData = MutableLiveData<ViewState<RepoUserName, ResponseStatus>>()
    val repoUserLiveData: LiveData<ViewState<RepoUserName, ResponseStatus>> = _repoUserLiveData

    fun getEmojis() {
        scope.launch(dispatcherProvider.ui) {
            _emojisLiveData.postValue(ViewState(status = ResponseStatus.LOADING))
            when (val response = emojiRepository.getEmojis()) {
                is Result.Success -> {
                    response.data.takeIf { it.emojiList.isNotEmpty() }?.let {
                        _emojisLiveData.postValue(ViewState(it.emojiList, ResponseStatus.SUCCESS))
                    }
                }
                is Result.Failure -> {

                }
            }
        }
    }

    fun getRepoByUser(username: String){
        scope.launch(dispatcherProvider.ui) {
            when (val response = repoUserNameRepository.getRepoByUserName(username)) {
                is Result.Success -> {
                    response.data?.let {
                        _repoUserLiveData.postValue(ViewState(it, ResponseStatus.SUCCESS))
                    }
                }
                is Result.Failure -> {

                }
            }
        }
    }
}