package br.com.felipepalm14.eventour.domain.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CupomDTO (
    @Expose @SerializedName("id")
    val id: Long,
    @Expose @SerializedName("eventId")
    val eventId: Long,
    @Expose @SerializedName("discount")
    val discount: Double
)