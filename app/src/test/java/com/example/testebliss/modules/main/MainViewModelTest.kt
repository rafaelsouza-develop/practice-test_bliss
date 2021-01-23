package com.example.testebliss.modules.main


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.testebliss.base.ViewState
import com.example.testebliss.models.EmojiList
import com.example.testebliss.repository.emoji.EmojiRepository
import com.example.testebliss.testUtils.TestCoroutineContextProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.example.testebliss.data.network.Result
import com.example.testebliss.models.Emoji
import com.example.testebliss.models.RepoUserName
import com.example.testebliss.models.ResponseStatus
import com.example.testebliss.repository.reposusername.RepoUserNameRepository
import com.example.testebliss.util.DispatcherProvider
import dev.jamile.githubapp.testUtils.createFlowTest
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    private lateinit var emojiEventObserver: Observer<ViewState<List<Emoji>, ResponseStatus>>

    private lateinit var repoEventObserver: Observer<ViewState<RepoUserName, ResponseStatus>>

    private lateinit var emojiRepo: EmojiRepository

    private lateinit var repoUserNameRepository: RepoUserNameRepository

    private val contextProvider = TestCoroutineContextProvider()

    private lateinit var emojiResponse: Result<EmojiList>

    private lateinit var repoResponse: Result<RepoUserName>

    private val query = "rafaelsouza-develop"

    private val testModule = module {
        single<DispatcherProvider> { TestCoroutineContextProvider() }
    }

    @Before
    fun setUp() {

        startKoin { modules(testModule) }
        emojiEventObserver = spyk(Observer { })

        emojiRepo = mockk()

        repoUserNameRepository = mockk()

        emojiResponse = mockk()

        repoResponse = mockk()

        viewModel = MainViewModel(contextProvider, emojiRepo, repoUserNameRepository).apply {
            emojisLiveData.observeForever(emojiEventObserver)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `WHEN get list emoji Api THEN change observer`() = runBlockingTest {

        coEvery { emojiRepo.getEmojis() } returns emojiResponse

        viewModel.apply {
            getEmojis()
            emojisLiveData.observeForever(emojiEventObserver)
        }

        val captor = mutableListOf<ViewState<List<Emoji>, ResponseStatus>>()
        verify { emojiEventObserver.onChanged(capture(captor)) }
    }

    @Test
    fun getRepoByUser() = runBlockingTest {
        val flow = createFlowTest(query)
        coEvery { repoUserNameRepository.getRepoByUserName(query) } returns repoResponse

        viewModel.apply {
            getEmojis()
            repoUserLiveData.observeForever(repoEventObserver)
        }

        val captor = mutableListOf<ViewState<RepoUserName, ResponseStatus>>()
        verify { repoEventObserver.onChanged(capture(captor)) }
    }
}