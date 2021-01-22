package com.example.testebliss.modules.googlerepos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testebliss.R
import com.example.testebliss.models.GoogleRepos
import com.example.testebliss.models.ResponseStatus
import kotlinx.android.synthetic.main.activity_google_repos.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GoogleReposActivity : AppCompatActivity() {

    private val viewModel: GoogleReposViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_repos)
        setupObserverViewState(viewModel)
        viewModel.getGoogleRepos(googleRepo = GOOGLE_REPO)
    }

    private fun setupObserverViewState(viewModel: GoogleReposViewModel) {
        viewModel.googleReposLiveData.observe(this, Observer { viewState ->
            when (viewState.status) {
                ResponseStatus.SUCCESS -> {
                    viewState.data?.let { setRecyclerViewList(it) }
                }
                ResponseStatus.ERROR -> {
                }
            }
        })
    }

    private fun setRecyclerViewList(googleRepos: List<GoogleRepos>) {

        recyclerGoogleRepos.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = GoogleReposAdapter(googleRepos)
        }
    }

    companion object {
        const val GOOGLE_REPO = "google"
    }
}