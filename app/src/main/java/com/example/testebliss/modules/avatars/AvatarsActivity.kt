package com.example.testebliss.modules.avatars

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testebliss.R
import com.example.testebliss.models.RepoUserName
import com.example.testebliss.models.ResponseStatus
import kotlinx.android.synthetic.main.activity_avatars.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AvatarsActivity : AppCompatActivity() {

    private val viewModel: AvatarsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avatars)
        setupObserverViewState(viewModel)
        viewModel.getAvatars()
    }

    private fun setupObserverViewState(viewModel: AvatarsViewModel) {
        viewModel.reposLiveData.observe(this, Observer { viewState ->
            when (viewState.status) {
                ResponseStatus.SUCCESS -> {
                    setRecyclerViewList(viewState.data!!)
                }

            }
        })
    }

    private fun setRecyclerViewList(repos: List<RepoUserName>) {
        recyclerAvatars.apply {
            layoutManager = GridLayoutManager(context, 4)
            adapter = AvatarsAdapter(repos)
        }
    }
}