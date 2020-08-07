package br.com.felipepalm14.eventour.domain.network

sealed class Result<out T> {
    data class Success<out T>	(val data: T)			: Result<T>()
    data class Failure			(val error: Throwable)	: Result<Nothing>()
}