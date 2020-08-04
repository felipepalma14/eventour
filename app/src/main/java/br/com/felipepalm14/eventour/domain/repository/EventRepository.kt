package br.com.felipepalm14.eventour.domain.repository

import br.com.felipepalm14.eventour.domain.database.EventDao
import br.com.felipepalm14.eventour.extensions.getEventEntity
import br.com.felipepalm14.eventour.utils.upsetOperation
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val remoteDataSource: EventRemoteDataSource,
    private val localDataSource: EventDao)
{
//    fun getCharacter(id: Int) = upsetOperation(
//        databaseQuery = { remoteDataSource.fetchAllEvents(id) },
//        networkCall = { remoteDataSource.getCharacter(id) },
//        saveCallResult = { localDataSource.insert(it) }
//    )

    fun getAllEvents() = upsetOperation(
        databaseQuery = { localDataSource.getAllEvents() },
        networkCall = { remoteDataSource.fetchAllEvents() },
        saveCallResult = { localDataSource.insertAll(it.map { event-> event.getEventEntity() }) }
    )
}