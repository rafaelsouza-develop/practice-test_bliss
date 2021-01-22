package com.example.testebliss.modules.emojislist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testebliss.R
import com.example.testebliss.models.Emoji
import com.example.testebliss.models.ResponseStatus
import kotlinx.android.synthetic.main.activity_emoji_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EmojiListActivity : AppCompatActivity() {

    private val viewModel: EmojiListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emoji_list)
        setupObserverViewState(viewModel)
        viewModel.getEmojis()
        setListners()
    }

    private fun setListners() {
        swipeRefresh.setOnRefreshListener {
            viewModel.getEmojis()
        }
    }

    private fun setupObserverViewState(viewModel: EmojiListViewModel) {
        viewModel.emojisLiveData.observe(this, Observer { viewState ->
            when (viewState.status) {
                ResponseStatus.SUCCESS -> {
                    swipeRefresh.isRefreshing = false
                    viewState.data?.let { setRecyclerViewList(it) }
                }
                ResponseStatus.ERROR -> {
                    swipeRefresh.isRefreshing = false
                }
                ResponseStatus.UNLOADING -> {
                    swipeRefresh.isRefreshing = false
                }
                ResponseStatus.LOADING -> {
                    swipeRefresh.isRefreshing = true
                }
                ResponseStatus.EMPTY_LIST -> {
                    swipeRefresh.isRefreshing = false
                }
            }
        })
    }

    private fun setRecyclerViewList(emojiList: List<Emoji>) {
        recyclerEmoji.apply {
            layoutManager = GridLayoutManager(context, 4)
            adapter = EmojiListAdapter(emojiList.toMutableList())
        }
    }


}