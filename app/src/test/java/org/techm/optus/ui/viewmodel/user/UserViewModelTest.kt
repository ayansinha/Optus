package org.techm.optus.ui.viewmodel.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.techm.optus.TestCoroutineRule
import org.techm.optus.data.model.user.UserModel
import org.techm.optus.data.network.APIService
import org.techm.optus.data.repository.UserRepository
import org.techm.optus.util.Constants.Companion.ERROR_MSG
import org.techm.optus.util.Result
import retrofit2.Response

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
    private lateinit var apiService: APIService

    @Mock
    private lateinit var userObserver: Observer<Result<Response<List<UserModel>>>>

    @Mock
    private lateinit var userList: Response<List<UserModel>>

    @Before
    fun setUp() {
        //todo
    }

    @Test
    fun testGivenUserResponseIsSuccess() {
        userRepository = UserRepository(apiService)
        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<UserModel>())
                .`when`(userRepository).getUsersApi()

            val userViewModel = UserViewModel(userRepository)
            userViewModel.getUserList().observeForever(userObserver)
            verify(userRepository).getUsersApi()
            verify(userObserver).onChanged(Result.success(userList))
            userViewModel.getUserList().removeObserver(userObserver)
        }
    }


    @Test
    fun testGivenUserResponseIsError() {
        testCoroutineRule.runBlockingTest {
            doThrow(RuntimeException(ERROR_MSG))
                .`when`(userRepository)
                .getUsersApi()

            val userViewModel = UserViewModel(userRepository)
            userViewModel.getUserList().observeForever(userObserver)
            verify(userRepository).getUsersApi()
            verify(userObserver).onChanged(Result.error(RuntimeException(ERROR_MSG).toString() , null))
            userViewModel.getUserList().removeObserver(userObserver)
        }
    }

}
