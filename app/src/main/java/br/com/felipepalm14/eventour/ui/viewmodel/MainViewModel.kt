package br.com.felipepalm14.eventour.ui.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.felipepalm14.eventour.domain.database.model.Event
import br.com.felipepalm14.eventour.domain.network.Result
import br.com.felipepalm14.eventour.domain.network.dto.EventDTO
import br.com.felipepalm14.eventour.domain.network.dto.UserCheckinDTO
import br.com.felipepalm14.eventour.domain.repository.EventRepository
import br.com.felipepalm14.eventour.extensions.getEventEntity
import br.com.felipepalm14.eventour.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class MainViewModel  @Inject constructor(
   private val repository: EventRepository
): BaseViewModel() {

   private val _events = MutableLiveData<List<Event>>()
   val events: LiveData<List<Event>>
      get() = _events

   private val _event = MutableLiveData<Event>()
   val event: LiveData<Event>
      get() = _event
   val	empty		= ObservableBoolean(false)
   val	fetching	= ObservableBoolean(true)
   val	refreshing= MutableLiveData(false)

   // Get local data
   fun fetchFromLocal() {
      isRefreshing(false)
      isFetching(true)
      getFromDatabase()
   }

   // Get remote data and overwrite local data
   fun fetchFromRemote() {
      isRefreshing(true)
      isFetching(false)
      getFromRemote()
   }


   private fun getFromDatabase() {
      CoroutineScope(Dispatchers.IO).launch {
         repository.fetchAllFromDatabase().apply {
            when(this) {
               is Result.Success	-> onGetFromDatabaseSuccess(data)
               is Result.Failure 	-> onGetFromDatabaseFailure(error)
            }
         }
      }
   }

   private fun getFromRemote() {
      CoroutineScope(Dispatchers.IO).launch {
         repository.fetchAllFromRemote().apply {
            when (this) {
               is Result.Success	-> onGetFromRemoteSuccess(data)
               is Result.Failure 	-> onGetFromRemoteFailure(error)
            }
         }
      }
   }

   private fun onGetFromDatabaseSuccess(collection: MutableList<Event>) {
      _events.postValue(collection.sortedByDescending { it.date }.toMutableList())
      isFetching(false)
      isRefreshing(false)
      isEmpty(collection.isEmpty())
   }

   private suspend fun onGetFromRemoteSuccess(response: Response<MutableList<EventDTO>>) {
      when(response.code()) {
         in 200 .. 299	-> onSuccess(response.body()!!.map { it.getEventEntity() }.toMutableList())
         else			-> onServiceError(response)
      }
   }

   private suspend fun onSuccess(collection: MutableList<Event>) {
      val	eventsResult	= repository.fetchAllFromDatabase()

      if (eventsResult is Result.Success) {
         update(collection)
      } else {
         if (eventsResult is Result.Failure) {
            onGetFromDatabaseFailure(eventsResult.error)
         }
      }
   }

   private suspend fun update(collection: MutableList<Event>) {

      repository.persist(collection).apply {
         when(this) {
            is Result.Success	-> getFromDatabase()
            is Result.Failure	-> onPersistFailure(error)
         }
      }
   }

   private fun onGetFromDatabaseFailure(throwable: Throwable) {
      CoroutineScope(Dispatchers.Main).launch {
         toast.value			= "Falha ao carregar itens da base de dados"
         isFetching(false)
         isRefreshing(false)
         throwable.printStackTrace()
         Timber.e(throwable)
      }
   }

   private fun onGetFromRemoteFailure(throwable: Throwable) {
      CoroutineScope(Dispatchers.Main).launch {
         toast.value			= "Falha ao carregar alguns itens"
         isFetching(false)
         isRefreshing(false)
         throwable.printStackTrace()
         Timber.e(throwable)
      }
   }

   private fun onPersistFailure(throwable: Throwable) {
      CoroutineScope(Dispatchers.Main).launch {
         toast.value			= "Falha ao sincronizar itens"
         isFetching(false)
         isRefreshing(false)
         throwable.printStackTrace()
         Timber.e(throwable)
      }
   }

   fun findEvent(identifier: Long?) {
      CoroutineScope(Dispatchers.IO).launch {
         identifier?.let { id ->
            when (val result = repository.fetchOneEventFromRemote(id)) {
               is Result.Success	-> onFetchOneSuccess(result.data)
               is Result.Failure 	-> {
                  CoroutineScope(Dispatchers.Main).launch {
                     toast.value		= "Falha ao atualizar evento, verifique sua conexão"
                  }
                  result.error.printStackTrace()
                  Timber.e(result.error)
               }
            }

         }
      }
   }
   private suspend fun onFetchOneSuccess(response: Response<EventDTO>) {
      when (response.code()) {
         in 200 .. 299	-> onResponseSuccess(response.body()?.getEventEntity())
         else			-> return
      }
   }


   private suspend fun onResponseSuccess(event: Event?) {
      event?.let {
         repository.persist(it).apply {
            when(this) {
               is Result.Success	-> getOneFromDatabase(event)
               is Result.Failure	-> onPersistFailure(error)
            }
         }

         repository.persist(it)
      }
   }

   private fun getOneFromDatabase(event: Event) {
      CoroutineScope(Dispatchers.IO).launch {
         repository.fetchOneFromDatabase(event.id).apply {
            when(this) {
               is Result.Success	-> onGetOneFromDatabaseSuccess(data)
               is Result.Failure 	-> onGetFromDatabaseFailure(error)
            }
         }
      }
   }

   private fun onGetOneFromDatabaseSuccess(event: Event) {
      _event.postValue(event)
   }

   suspend fun checkin(userCheckin: UserCheckinDTO) = repository.doCheckin(userCheckin)



   private fun isFetching(fetching: Boolean) {
      CoroutineScope(Dispatchers.Main).launch {
         this@MainViewModel.fetching.set(fetching)
      }
   }

   private fun isRefreshing(refresh: Boolean) {
      CoroutineScope(Dispatchers.Main).launch {
         this@MainViewModel.refreshing.value = refresh
      }
   }

   private fun isEmpty(empty: Boolean) {
      CoroutineScope(Dispatchers.Main).launch {
         this@MainViewModel.empty.set(empty)
      }
   }
}