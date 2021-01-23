package com.example.testebliss.modules.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.testebliss.R
import com.example.testebliss.base.BaseActivity
import com.example.testebliss.models.ResponseStatus
import com.example.testebliss.modules.avatars.AvatarsActivity
import com.example.testebliss.modules.emojislist.EmojiListActivity
import com.example.testebliss.modules.googlerepos.GoogleReposActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_emoji_list.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity() {

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
        btnAvatarList.setOnClickListener { goToAvatarList() }
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
            if (viewState.status == ResponseStatus.SUCCESS) {
                setImageEmoji(viewState.data?.random()?.url!!)
            }
            else if (viewState.status == ResponseStatus.ERROR) { showError() }
        })

        viewModel.repoUserLiveData.observe(this, Observer { viewState ->
            if (viewState.status == ResponseStatus.SUCCESS) { setImageEmoji(viewState.data?.avatarUrl!!) }
            else if (viewState.status == ResponseStatus.ERROR) { showError() }
        })
    }

    private fun showError() {
        swipeRefresh.isRefreshing = false
        showDialogError("Sorry!", "I'm down! Try again.")
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

    private fun goToAvatarList() {
        startActivity(Intent(this, AvatarsActivity::class.java))
    }

}