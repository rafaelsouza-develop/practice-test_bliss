package com.example.testebliss.modules.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.testebliss.R
import com.example.testebliss.models.ResponseStatus
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupObserverViewState(viewModel)
        viewModel.getEmojis()
        setListners()
    }

    private fun setListners() {
        btnRandomEmoji.setOnClickListener { getRandomEmoji() }
    }

    private fun getRandomEmoji() {
        val emoji = viewModel.emojisLiveData.value?.data?.random()
        emoji?.url?.let { setImageEmoji(it) }
    }

    private fun setupObserverViewState(viewModel: MainViewModel) {
        viewModel.emojisLiveData.observe(this, Observer { viewState ->
            when (viewState.status) {
                ResponseStatus.SUCCESS -> {
                    setImageEmoji(viewState.data?.firstOrNull()?.url!!)
                }
                ResponseStatus.ERROR -> {
                }
            }
        })
    }

    private fun setImageEmoji(emojiUrl: String) {
        Picasso.get().load(emojiUrl).into(ivEmoji)
    }
}