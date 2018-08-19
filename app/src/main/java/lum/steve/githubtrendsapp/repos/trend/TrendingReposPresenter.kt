package lum.steve.githubtrendsapp.repos.trend

import lum.steve.githubtrendsapp.base.BasePresenterImpl
import lum.steve.githubtrendsapp.repos.data.ReposRepository
import lum.steve.githubtrendsapp.repos.data.remote.model.GitHubSearchResults
import lum.steve.githubtrendsapp.repos.data.remote.model.Item
import rx.Scheduler

class TrendingReposPresenter(private val reposRepository: ReposRepository, ioScheduler: Scheduler, mainScheduler: Scheduler) :
        BasePresenterImpl<TrendingReposContract.View>(ioScheduler, mainScheduler), TrendingReposContract.Presenter {


    override fun loadTrendingRepos() {
        addSubscription(reposRepository.searchRepos("android")
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(this::showResults, this::handleError))
    }

    private fun showResults(list: GitHubSearchResults) {
        mView!!.showResults(list)
        mView!!.setLoadingIndicator(false)
    }

    private fun handleError(error: Throwable) {
        mView!!.setLoadingIndicator(false)
        mView!!.showError()
    }

    override fun onItemClick(item: Item) {
        mView!!.showRepoDetailsScreen(item)
    }
}