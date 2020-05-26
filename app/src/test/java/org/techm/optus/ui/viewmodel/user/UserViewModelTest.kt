package org.techm.optus.ui.viewmodel.user

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
import org.techm.optus.ui.viewmodel.TestCoroutineRule
import org.techm.optus.data.model.user.UserModel
import org.techm.optus.data.repository.UserRepository
import org.techm.optus.util.Constants.Companion.ERROR_MSG
import org.techm.optus.util.Result

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var userObserver: Observer<Result<List<UserModel>>>

    @Test
    fun testGivenUserResponseWhenSuccess() {

        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<UserModel>())
                .`when`(userRepository).getUsers()

            val userViewModel = UserViewModel(userRepository)
            userViewModel.getUserList().observeForever(userObserver)
            verify(userRepository).getUsers()
            verify(userObserver).onChanged(Result.success(emptyList()))
            userViewModel.getUserList().removeObserver(userObserver)
        }
    }


    @Test
    fun testGivenUserResponseWhenError() {
        testCoroutineRule.runBlockingTest {
            doThrow(RuntimeException(ERROR_MSG))
                .`when`(userRepository)
                .getUsers()

            val userViewModel = UserViewModel(userRepository)
            userViewModel.getUserList().observeForever(userObserver)
            verify(userRepository).getUsers()
            verify(userObserver).onChanged(Result.error(ERROR_MSG , null))
            userViewModel.getUserList().removeObserver(userObserver)
        }
    }

}
