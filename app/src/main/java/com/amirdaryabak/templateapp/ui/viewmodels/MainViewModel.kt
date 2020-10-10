package com.amirdaryabak.templateapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.amirdaryabak.templateapp.repositories.MainRepository

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : ViewModel() {


}