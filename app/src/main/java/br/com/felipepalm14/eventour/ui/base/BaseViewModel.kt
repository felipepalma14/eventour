package br.com.felipepalm14.eventour.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber

abstract class BaseViewModel: ViewModel() {


    val	toast		: MutableLiveData<String> = MutableLiveData()

    protected open fun onServiceError(response: Response<*>) {
        CoroutineScope(Dispatchers.Main).launch {
            toast.value			= "Falha ao carregar dados"
            Timber.e("[Remote Response Path] ${response.raw().request.url}")
            Timber.e("[Remote Response Code] ${response.code()}")
        }
    }
}