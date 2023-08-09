package com.linkedlist.linkllet.feature.home.folder

import com.linkedlist.linkllet.core.data.repository.LinkRepository
import com.linkedlist.linkllet.core.data.repository.fake.FakeLinkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AddEditFolderViewModelTest {
    private lateinit var viewModel: AddEditFolderViewModel
    private lateinit var linkRepository: LinkRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)

        linkRepository = FakeLinkRepository()
        viewModel = AddEditFolderViewModel(linkRepository = linkRepository)

        val foldersToInsert = (1..10).map { "folder$it" }.toMutableList()
        foldersToInsert.shuffle()
        foldersToInsert.forEach {
            linkRepository.addFolder(it).collect()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun onFinish() {
        Dispatchers.resetMain()
    }

    @Test
    fun validFolderAdded_FolderAdded() = runTest {
//        val emittedEvents = mutableListOf<Event>()
//        val eventsFlow = viewModel.eventsFlow
//        val job = launch {
//            eventsFlow.toList(emittedEvents)
//        }
        viewModel.updateFolderName("newFolder")

        viewModel.addFolder()

        val folders = linkRepository.getFolders().last().getOrThrow()
        assertEquals(11, folders.size)
        assertEquals("newFolder", folders.last().name)

//        val expectedEvents = listOf(Event.CloseScreen, Event.ShowToast("폴더가 추가되었어요"))
//        assertEquals(expectedEvents, emittedEvents)
//        job.cancel()
    }

    @Test
    fun whiteSpaceFolderAdded_ErrorFlagSet() = runTest {
        viewModel.updateFolderName(" ")

        viewModel.addFolder()

        val folders = linkRepository.getFolders().single().getOrThrow()
        assertEquals(10, folders.size)
        val uiState = viewModel.uiState.value
        assertTrue(uiState.error)
    }
}