package br.com.felipepalm14.eventour.domain.network

import br.com.felipepalm14.eventour.EVENTS
import br.com.felipepalm14.eventour.domain.network.dto.EventDTO
import retrofit2.Response
import retrofit2.http.GET

interface EventsAPI {
    @GET(EVENTS)
    suspend fun fetchAllEvents(): Response<List<EventDTO>>


//    @POST(CHECKIN)
//    suspend fun doCheckin(): Response<EventDTO>
}