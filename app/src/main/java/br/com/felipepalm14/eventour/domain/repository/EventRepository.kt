package br.com.felipepalm14.eventour.domain.repository

import br.com.felipepalm14.eventour.domain.database.EventDao
import br.com.felipepalm14.eventour.domain.database.model.Event
import br.com.felipepalm14.eventour.domain.network.Result
import br.com.felipepalm14.eventour.domain.network.dto.EventDTO
import br.com.felipepalm14.eventour.domain.network.dto.UserCheckinDTO
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val remoteDataSource: EventRemoteDataSource,
    private val localDataSource: EventDao) : Repository() {

    suspend fun fetchOneEventFromRemote(id: Long) = HandleResult {remoteDataSource.fetchOneEvent(id)}

    suspend fun fetchOneFromDatabase(id: Long): Result<Event> {
        return HandleResult { localDataSource.getEvent(id) }
    }

    suspend fun doCheckin(userCheckin: UserCheckinDTO): Result<Response<ResponseBody>> {
        return HandleResult { remoteDataSource.doCheckin(userCheckin) }
    }

    suspend fun fetchAllFromRemote(): Result<Response<MutableList<EventDTO>>> {
        return HandleResult { remoteDataSource.fetchAllEvents() }
    }

    suspend fun fetchAllFromDatabase(): Result<MutableList<Event>> {
        return HandleResult { localDataSource.getAllEvents() }
    }

    suspend fun persist(event: Event): Result<Unit> {
        return HandleResult {
            localDataSource.insert(event)
        }
    }

    suspend fun persist(collection: List<Event>): Result<Unit> {
        return HandleResult {
            localDataSource.insertAll(collection)
        }
    }
}