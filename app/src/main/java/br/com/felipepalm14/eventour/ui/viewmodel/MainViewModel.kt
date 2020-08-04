package br.com.felipepalm14.eventour.ui.viewmodel

import br.com.felipepalm14.eventour.domain.repository.EventRepository
import br.com.felipepalm14.eventour.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel  @Inject constructor(
   private val repository: EventRepository
): BaseViewModel() {

   val events by lazy { repository.getAllEvents() }

}