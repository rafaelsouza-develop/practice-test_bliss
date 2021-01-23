package com.example.testebliss.modules.googlerepos

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testebliss.R
import com.example.testebliss.models.ResponseStatus
import kotlinx.android.synthetic.main.activity_google_repos.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GoogleReposActivity : AppCompatActivity() {

    private val viewModel: GoogleReposViewModel by viewModel()
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: GoogleReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_repos)
        mLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mAdapter = GoogleReposAdapter(mutableListOf())
        setupObserverViewState(viewModel)
        setListners()
        setRecyclerViewList()
        getGoogleRepos()
    }

    private fun setupObserverViewState(viewModel: GoogleReposViewModel) {
        viewModel.googleReposLiveData.observe(this, Observer { viewState ->
            when (viewState.status) {
                ResponseStatus.SUCCESS -> {
                    viewState.data?.let { mAdapter.addData(it) }
                    swipeRefresh.isRefreshing = false
                }
                ResponseStatus.LOADING -> swipeRefresh.isRefreshing = true
                ResponseStatus.UNLOADING -> swipeRefresh.isRefreshing = false
                ResponseStatus.ERROR -> showError()
                else -> swipeRefresh.isRefreshing = false
            }
        })
    }

    private fun setListners() {
        swipeRefresh.setOnRefreshListener {
            viewModel.page = 1
            mAdapter.clearData()
            getGoogleRepos()
        }
    }

    private fun setRecyclerViewList() {
        with(recyclerGoogleRepos) {
            adapter = mAdapter
            layoutManager = mLayoutManager
            setHasFixedSize(true)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val lastPosition = mLayoutManager.findLastVisibleItemPosition()
                    if (lastPosition >= (recyclerView.adapter?.itemCount?.minus(10)!!)) {
                        getGoogleRepos()
                    }
                }
            })
        }
    }

    private fun showError() {
        swipeRefresh.isRefreshing = false
        Toast.makeText(this, "Sorry! I'm down! Try again.", Toast.LENGTH_LONG).show()
    }

    private fun getGoogleRepos() {
        viewModel.getGoogleRepos(googleRepo = GOOGLE_REPO)
    }

    companion object {
        const val GOOGLE_REPO = "google"
    }
}