package com.uipractice.roomfinder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T: ViewBinding, U: ViewModel>(
    private val view: (LayoutInflater) -> T,
    private val viewModelClass: U): AppCompatActivity(), View.OnClickListener {

    lateinit var binding: T
    lateinit var viewModel: U

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = view.invoke(layoutInflater)
        viewModel = ViewModelProvider(this)[viewModelClass::class.java]
        setContentView(binding.root)
    }

}