package com.example.testebliss.modules.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testebliss.CoreApplication
import com.example.testebliss.CoreApplication.Companion.database
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
        scope.launch(dispatcherProvider.io) {
            _emojisLiveData.postValue(ViewState(status = ResponseStatus.LOADING))
            val emojiList = database?.emojiDao()?.getAllEmojis()
            if (emojiList!!.isEmpty()) {
                when (val response = emojiRepository.getEmojis()) {
                    is Result.Success -> {
                        response.data.takeIf { it.emojiList.isNotEmpty() }?.let {
                            it.emojiList.forEach {
                                database?.emojiDao()?.insertEmoji(it)
                            }
                            _emojisLiveData.postValue(
                                ViewState(
                                    it.emojiList,
                                    ResponseStatus.SUCCESS
                                )
                            )
                        }
                    }
                    is Result.Failure -> {
                        _emojisLiveData.postValue(ViewState(status = ResponseStatus.ERROR))
                    }
                }
            } else {
                _emojisLiveData.postValue(
                    ViewState(
                        emojiList,
                        ResponseStatus.SUCCESS
                    )
                )
            }

        }
    }

    fun getRepoByUser(username: String){
        scope.launch(dispatcherProvider.io) {
            when (val response = repoUserNameRepository.getRepoByUserName(username)) {
                is Result.Success -> {
                    response.data.let {
                        database?.reposDao()?.insertRepo(it)
                        _repoUserLiveData.postValue(ViewState(it, ResponseStatus.SUCCESS))
                    }
                }
                is Result.Failure -> {

                }
            }
        }
    }
}