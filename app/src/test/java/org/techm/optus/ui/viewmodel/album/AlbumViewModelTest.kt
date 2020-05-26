package org.techm.optus.ui.viewmodel.album

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.techm.optus.data.model.album.AlbumModel
import org.techm.optus.data.repository.AlbumRepository
import org.techm.optus.ui.viewmodel.TestCoroutineRule
import org.techm.optus.util.Constants.Companion.ERROR_MSG
import org.techm.optus.util.Result

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AlbumViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    private val id = 5
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var albumRepository: AlbumRepository

    @Mock
    private lateinit var albumObserver: Observer<Result<List<AlbumModel>>>

    @Test
    fun `test given when album response is success`() {

        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<AlbumModel>())
                .`when`(albumRepository)
                .getAlbumApi(id)

            val albumViewModel = AlbumViewModel(albumRepository)
            albumViewModel.getAlbumList(id).observeForever(albumObserver)
            verify(albumRepository).getAlbumApi(id)
            verify(albumObserver).onChanged(Result.success(emptyList()))
            albumViewModel.getAlbumList(id).removeObserver(albumObserver)
        }
    }

    @Test
    fun `test given when album response is failure`() {
        testCoroutineRule.runBlockingTest {
            doThrow(RuntimeException(ERROR_MSG))
                .`when`(albumRepository)
                .getAlbumApi(id)

            val albumViewModel = AlbumViewModel(albumRepository)
            albumViewModel.getAlbumList(id).observeForever(albumObserver)
            verify(albumRepository).getAlbumApi(id)
            verify(albumObserver).onChanged(Result.error(ERROR_MSG, null))
            albumViewModel.getAlbumList(id).removeObserver(albumObserver)
        }
    }
}