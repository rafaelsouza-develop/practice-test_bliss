package com.example.testebliss.modules.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testebliss.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getEmojis()
    }
}