package lum.steve.githubtrendsapp.repos.data

import lum.steve.githubtrendsapp.repos.data.remote.GithubService
import lum.steve.githubtrendsapp.repos.data.remote.model.GitHubSearchResults
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import rx.Observable
import rx.observers.TestSubscriber
import java.io.IOException

class ReposRepositoryImplTest {


    private lateinit var reposRepository: ReposRepositoryImpl

    @Mock
    private lateinit var githubService: GithubService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        reposRepository = ReposRepositoryImpl(githubService)
    }

    /**
     * All is well test
     */
    @Test
    fun loadTrendingRepos_200OkResponse() {
        //Given
        `when`(githubService.loadTrendingRepos(anyString(), anyString(), anyString())).thenReturn(Observable.just(Dummy.getGitHubSearchResults()))

        //When
        val subscriber: TestSubscriber<GitHubSearchResults> = TestSubscriber()
        reposRepository.searchRepos("").subscribe(subscriber)

        //Then
        subscriber.awaitTerminalEvent()
        subscriber.assertNoErrors()

        assertNotNull(subscriber.onNextEvents)
        val repos = subscriber.onNextEvents[0]
        assertNotNull(repos)
        assertEquals(Dummy.REPO_ONE_NAME, repos.items!![0].name)
        assertEquals(Dummy.REPO_TWO_NAME, repos.items!![1].name)

        verify(githubService).loadTrendingRepos(anyString(), anyString(), anyString())
    }

    /**
     * IOException on first try - retry kicks in and is successful on next attempt
     */
    @Test
    fun loadTrendingRepos_IOExceptionRetry200OkResponse() {
        //Given
        `when`(githubService.loadTrendingRepos(anyString(), anyString(), anyString())).thenReturn(Observable.error(IOException()), Observable.just(Dummy.getGitHubSearchResults()))

        //When
        val subscriber: TestSubscriber<GitHubSearchResults> = TestSubscriber()
        reposRepository.searchRepos("").subscribe(subscriber)

        //Then
        subscriber.awaitTerminalEvent()
        subscriber.assertError(IOException::class.java)
    }

}