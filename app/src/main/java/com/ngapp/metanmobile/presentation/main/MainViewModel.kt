package com.ngapp.metanmobile.presentation.main

import androidx.lifecycle.ViewModel
import com.ngapp.metanmobile.provider.ThemeProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val themeProvider: ThemeProvider) : ViewModel() {

    fun themeProvider() = themeProvider
}