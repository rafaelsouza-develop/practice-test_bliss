package com.example.testebliss.modules.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testebliss.base.BaseViewModel
import com.example.testebliss.base.ViewState
import com.example.testebliss.data.network.Result
import com.example.testebliss.models.Emoji
import com.example.testebliss.models.ResponseStatus
import com.example.testebliss.repository.EmojiRepository
import com.example.testebliss.util.DispatcherProvider
import kotlinx.coroutines.launch

class MainViewModel(
    private val dispatcherProvider: DispatcherProvider,
    private val emojiRepository: EmojiRepository
) :
    BaseViewModel(dispatcherProvider) {

    private val _emojisLiveData = MutableLiveData<ViewState<List<Emoji>, ResponseStatus>>()
    val emojisLiveData: LiveData<ViewState<List<Emoji>, ResponseStatus>> = _emojisLiveData

    fun getEmojis() {
        scope.launch(dispatcherProvider.ui) {
            _emojisLiveData.postValue(ViewState(status = ResponseStatus.LOADING))
            when (val response = emojiRepository.getEmojis()) {
                is Result.Success -> {


                }
                is Result.Failure -> {

                }

            }
        }
    }
}