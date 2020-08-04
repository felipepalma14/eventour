package br.com.felipepalm14.eventour.extensions

import br.com.felipepalm14.eventour.domain.database.model.Cupom
import br.com.felipepalm14.eventour.domain.network.dto.CupomDTO

fun CupomDTO.getCupomEntity():Cupom {
    return Cupom(id = id,eventId = eventId,discount = discount)
}

