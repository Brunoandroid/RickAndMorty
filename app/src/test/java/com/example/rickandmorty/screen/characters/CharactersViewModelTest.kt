package com.example.rickandmorty.screen.characters

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.example.rickandmorty.data.model.character.Result
import com.example.rickandmorty.data.repository.CharactersRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class, ObsoleteCoroutinesApi::class)
class CharactersViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var repository: CharactersRepository

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = Mockito.mock(CharactersRepository::class.java)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `characters flow emits expected paging data`() = runBlockingTestCompat {
        val expected = listOf(
            Result(id = 1, name = "Rick Sanchez"),
            Result(id = 2, name = "Morty Smith")
        )
        val pagingData = PagingData.from(expected)
        whenever(repository.getAllCharacters()).thenReturn(flowOf(pagingData))

        val viewModel = CharactersViewModel(repository)

        val emission = viewModel.characters.first()

        val differ = AsyncPagingDataDiffer(
            diffCallback = testResultDiff,
            updateCallback = noopListUpdateCallback,
            workerDispatcher = testDispatcher
        )
        differ.submitData(emission)
        testDispatcher.advanceUntilIdle()

        val snapshot = differ.snapshot().items
        assertEquals(expected.size, snapshot.size)
        assertEquals(expected[0], snapshot[0])
        assertEquals(expected[1], snapshot[1])
    }

    private val testResultDiff = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean = oldItem == newItem
    }

    private val noopListUpdateCallback = object : ListUpdateCallback {
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
        override fun onMoved(fromPosition: Int, toPosition: Int) {}
        override fun onChanged(position: Int, count: Int, payload: Any?) {}
    }
}

// Compatibility helper for old kotlinx-coroutines-test (1.4.x)
@OptIn(ExperimentalCoroutinesApi::class, ObsoleteCoroutinesApi::class)
private fun runBlockingTestCompat(block: suspend () -> Unit) {
    val dispatcher = TestCoroutineDispatcher()
    try {
        Dispatchers.setMain(dispatcher)
        dispatcher.runBlockingTest { block() }
    } finally {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }
}
