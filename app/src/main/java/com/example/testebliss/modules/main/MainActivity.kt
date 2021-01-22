package com.example.testebliss.modules.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.testebliss.R
import com.example.testebliss.models.ResponseStatus
import com.example.testebliss.modules.emojislist.EmojiListActivity
import com.example.testebliss.modules.googlerepos.GoogleReposActivity
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
        btnEmojiList.setOnClickListener { goToEmojiList() }
        btnGoogleRepos.setOnClickListener { goToGoogleRepos() }
        btnSeachRepo.setOnClickListener { searchRepoByUsername(edtNameRepo.text.toString()) }
    }

    private fun searchRepoByUsername(username: String?) {
        username.takeIf { it!!.isNotEmpty() }?.let { viewModel.getRepoByUser(it) }
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

        viewModel.repoUserLiveData.observe(this, Observer { viewState ->
            when (viewState.status) {
                ResponseStatus.SUCCESS -> {
                    setImageEmoji(viewState.data?.avatarUrl!!)
                }
                ResponseStatus.ERROR -> {
                }
            }
        })
    }

    private fun setImageEmoji(emojiUrl: String) {
        Picasso.get().load(emojiUrl).into(ivEmoji)
    }

    private fun goToEmojiList() {
        startActivity(Intent(this, EmojiListActivity::class.java))
    }

    private fun goToGoogleRepos() {
        startActivity(Intent(this, GoogleReposActivity::class.java))
    }
}