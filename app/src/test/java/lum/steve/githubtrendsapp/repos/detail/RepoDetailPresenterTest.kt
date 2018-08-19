package lum.steve.githubtrendsapp.repos.detail

import lum.steve.githubtrendsapp.repos.data.Dummy
import lum.steve.githubtrendsapp.repos.data.ReposRepository
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import rx.Observable
import rx.schedulers.Schedulers
import java.io.IOException

class RepoDetailPresenterTest {

    @Mock
    private lateinit var reposRepository: ReposRepository
    @Mock
    private lateinit var view: RepoDetailContract.View

    private lateinit var presenter: RepoDetailPresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = RepoDetailPresenter(reposRepository, Schedulers.immediate(), Schedulers.immediate())
        presenter.attachView(view)
    }

    /**
     * fetchRepoInfo s successful and show results in UI
     */
    @Test
    fun loadTrendingRepos_showResults() {
        val repoDetailInfo = Dummy.getRepoDetailInfo()
        Mockito.`when`(reposRepository.fetchRepoInfo(anyString(), anyString())).thenReturn(Observable.just(repoDetailInfo))

        presenter.fetchRepoInfo("square", "retrofit")

        Mockito.verify(view).showRepoDetails(repoDetailInfo)
    }

    /**
     * IOException shows error message
     */
    @Test
    fun loadTrendingRepos_error() {
        Mockito.`when`(reposRepository.fetchRepoInfo(anyString(), anyString())).thenReturn(Observable.error(IOException()))

        presenter.fetchRepoInfo("square", "retrofit")

        Mockito.verify(view).showError()
    }
}