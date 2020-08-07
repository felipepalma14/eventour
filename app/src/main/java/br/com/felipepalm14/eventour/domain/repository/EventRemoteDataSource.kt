package br.com.felipepalm14.eventour.domain.repository

import br.com.felipepalm14.eventour.domain.network.EventsAPI
import br.com.felipepalm14.eventour.domain.network.dto.UserCheckinDTO
import javax.inject.Inject

class EventRemoteDataSource @Inject constructor(
    private val eventsAPI: EventsAPI
) {

    suspend fun fetchOneEvent(id: Long) = eventsAPI.fetchOneEvent(id)

    suspend fun fetchAllEvents() =  eventsAPI.fetchAllEvents()

    suspend fun doCheckin(userCheckin: UserCheckinDTO) = eventsAPI.doCheckin(userCheckin)
}