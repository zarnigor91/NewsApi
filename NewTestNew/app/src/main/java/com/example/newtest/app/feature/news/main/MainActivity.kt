package com.example.newtest.app.feature.news.main

import android.os.Bundle
import com.example.newtest.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import moxy.MvpAppCompatActivity
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), HasAndroidInjector, IMainView {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}