package br.com.felipepalm14.eventour.domain.repository

import br.com.felipepalm14.eventour.domain.network.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class Repository {

    @Suppress("FunctionName")
    protected suspend fun <T> HandleResult(call: suspend () -> T) : Result<T> {

        return withContext(Dispatchers.Default) {
            try {
                Result.Success(call.invoke())
            } catch (exception: Exception) {
                Result.Failure(exception)
            }
        }
    }
}