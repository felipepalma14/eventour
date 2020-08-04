package br.com.felipepalm14.eventour.extensions

import br.com.felipepalm14.eventour.domain.database.model.Cupom
import br.com.felipepalm14.eventour.domain.database.model.Event
import br.com.felipepalm14.eventour.domain.network.dto.EventDTO

fun EventDTO.getEventEntity(): Event{
    return Event(
        id = id,
        title = title,
        image = image,
        cupons = getCupomEntity(),
        date = date,
        description = description,
        latitude = latitude,
        longitude = longitude,
        price = price)
}

fun EventDTO.getCupomEntity(): ArrayList<Cupom> {
    return ArrayList(cupons.map { it.getCupomEntity()})
}