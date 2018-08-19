package lum.steve.githubtrendsapp.repos.trend

import lum.steve.githubtrendsapp.repos.data.Dummy
import lum.steve.githubtrendsapp.repos.data.ReposRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import rx.Observable
import rx.schedulers.Schedulers
import java.io.IOException

class TrendingReposPresenterTest {

    @Mock
    private lateinit var reposRepository: ReposRepository
    @Mock
    private lateinit var view: TrendingReposContract.View

    private lateinit var presenter: TrendingReposPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = TrendingReposPresenter(reposRepository, Schedulers.immediate(), Schedulers.immediate())
        presenter.attachView(view)
    }

    /**
     * load repos is successful and show results in UI
     */
    @Test
    fun loadTrendingRepos_showResults() {
        val gitHubRepos = Dummy.getGitHubSearchResults()
        `when`(reposRepository.searchRepos(anyString())).thenReturn(Observable.just(gitHubRepos))

        presenter.loadTrendingRepos()

        verify(view).showResults(gitHubRepos)
        verify(view).setLoadingIndicator(false)
    }

    /**
     * IOException shows error message
     */
    @Test
    fun loadTrendingRepos_error() {
        `when`(reposRepository.searchRepos(anyString())).thenReturn(Observable.error(IOException()))

        presenter.loadTrendingRepos()

        verify(view).setLoadingIndicator(false)
        verify(view).showError()
    }
}