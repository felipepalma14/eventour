package br.com.felipepalm14.eventour.ui.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }
}