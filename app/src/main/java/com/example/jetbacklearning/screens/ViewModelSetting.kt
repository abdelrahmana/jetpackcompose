package com.example.jetbacklearning.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class ViewModelSetting  : ViewModel() {
    private val _eventFlow = MutableSharedFlow<String>()
    val eventFlow: SharedFlow<String> = _eventFlow
    fun setAddValue() {
        viewModelScope.launch {
            _eventFlow.emit(System.currentTimeMillis().toString())
        }
    }
}