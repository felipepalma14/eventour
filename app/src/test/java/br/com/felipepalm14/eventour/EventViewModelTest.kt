package br.com.felipepalm14.eventour

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.felipepalm14.eventour.domain.database.model.Event
import br.com.felipepalm14.eventour.domain.network.Result
import br.com.felipepalm14.eventour.domain.network.dto.EventDTO
import br.com.felipepalm14.eventour.domain.repository.EventRepository
import br.com.felipepalm14.eventour.extensions.fromJson
import br.com.felipepalm14.eventour.ui.viewmodel.MainViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class EventViewModelTest {


    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @ObsoleteCoroutinesApi
    private var mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var eventViewModel: MainViewModel

    @Mock
    private lateinit var repository: EventRepository //= mock(EventRepository::class.java)

    @Mock
    private lateinit var eventObserver : Observer<List<Event>>

    private val jsonEvents = Gson().fromJson<List<Event>>(JSON_EVENTS)
    @ObsoleteCoroutinesApi
    @Before
    fun init() {
        Dispatchers.setMain(mainThreadSurrogate)
        eventViewModel = com.nhaarman.mockitokotlin2.spy(MainViewModel(repository))
    }

    @Test
    fun testInteraction() {
        testCoroutineRule.runBlockingTest {
            eventViewModel.fetchFromRemote()
            verify(repository).fetchAllFromRemote()
            eventViewModel.events.removeObserver(eventObserver)

        }

    }

    @ObsoleteCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}