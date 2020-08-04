package br.com.felipepalm14.eventour.domain.repository

import br.com.felipepalm14.eventour.domain.network.BaseDataSource
import br.com.felipepalm14.eventour.domain.network.EventsAPI
import javax.inject.Inject

class EventRemoteDataSource @Inject constructor(
    private val eventsAPI: EventsAPI
) : BaseDataSource() {

    suspend fun fetchAllEvents() = getResult { eventsAPI.fetchAllEvents() }
}