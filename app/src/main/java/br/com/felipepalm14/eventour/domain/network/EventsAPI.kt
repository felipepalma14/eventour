package br.com.felipepalm14.eventour.domain.network

import br.com.felipepalm14.eventour.CHECKIN
import br.com.felipepalm14.eventour.EVENT
import br.com.felipepalm14.eventour.EVENTS
import br.com.felipepalm14.eventour.domain.network.dto.EventDTO
import br.com.felipepalm14.eventour.domain.network.dto.UserCheckinDTO
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventsAPI {

    @GET(EVENT)
    suspend fun fetchOneEvent(@Path("id") id: Long): Response<EventDTO>

    @GET(EVENTS)
    suspend fun fetchAllEvents(): Response<MutableList<EventDTO>>

    @POST(CHECKIN)
    suspend fun doCheckin(@Body userCheckin: UserCheckinDTO): Response<ResponseBody>
}