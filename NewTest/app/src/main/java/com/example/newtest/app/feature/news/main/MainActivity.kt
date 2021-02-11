package com.example.newtest.app.feature.news.main

import android.os.Bundle
import com.example.newtest.app.feature.news.main.di.IMainDaggerComponent
import com.example.newtest.databinding.ActivityMainBinding
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager

import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), IHasComponent<IMainDaggerComponent>, IMainView {

    @Inject
    @InjectPresenter
    lateinit var mainPresenter: MainPresenter


    private val binding by lazy {  ActivityMainBinding.inflate(layoutInflater)}

    override fun getComponent(): IMainDaggerComponent =
        IMainDaggerComponent.create(XInjectionManager.findComponent())

    override fun onCreate(savedInstanceState: Bundle?) {
        XInjectionManager.bindComponent(this).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}