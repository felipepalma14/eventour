package br.com.felipepalm14.eventour.domain.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.io.Serializable

data class Cupom (
    @ColumnInfo(name = "cupom_id") var id: Long = 0L,
    @ColumnInfo var eventId: Long = 0L,
    @ColumnInfo var discount: Double = 0.0
): Serializable