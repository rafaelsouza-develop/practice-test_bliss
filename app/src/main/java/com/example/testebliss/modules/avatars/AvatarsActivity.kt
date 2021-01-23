package com.example.testebliss.modules.avatars

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testebliss.R
import com.example.testebliss.base.BaseActivity
import com.example.testebliss.models.RepoUserName
import com.example.testebliss.models.ResponseStatus
import kotlinx.android.synthetic.main.activity_avatars.*
import kotlinx.android.synthetic.main.activity_avatars.swipeRefresh
import kotlinx.android.synthetic.main.activity_google_repos.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AvatarsActivity : BaseActivity(), AvatarsAdapter.AvatarAdapterListners {

    private val viewModel: AvatarsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avatars)
        setupObserverViewState(viewModel)
        setListners()
        viewModel.getAvatars()
    }

    private fun setupObserverViewState(viewModel: AvatarsViewModel) {
        viewModel.reposLiveData.observe(this, Observer { viewState ->
            when (viewState.status) {
                ResponseStatus.SUCCESS -> {
                    swipeRefresh.isRefreshing = false
                    setRecyclerViewList(viewState.data!!)
                }
                ResponseStatus.LOADING -> swipeRefresh.isRefreshing = true
                ResponseStatus.UNLOADING -> swipeRefresh.isRefreshing = false
                ResponseStatus.ERROR -> showError()
            }
        })
    }

    private fun setListners() {
        swipeRefresh.setOnRefreshListener {
            viewModel.getAvatars()
        }
    }

    private fun setRecyclerViewList(repos: List<RepoUserName>) {
        recyclerAvatars.apply {
            layoutManager = GridLayoutManager(context, SPAN_COUNT_ADAPTER)
            adapter = AvatarsAdapter(repos, this@AvatarsActivity)
        }
    }

    private fun showError() {
        swipeRefresh.isRefreshing = false
        showDialogError(
            getString(R.string.message_error_title_sorry),
            getString(R.string.message_error_imdown)
        )
    }

    override fun onRemoveBD(avatar: RepoUserName) {
        viewModel.onRemoveBD(avatar)
    }

    companion object {
        const val SPAN_COUNT_ADAPTER = 4
    }
}