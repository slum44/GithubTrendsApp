package lum.steve.githubtrendsapp.repos.detail

import lum.steve.githubtrendsapp.base.BasePresenterImpl
import lum.steve.githubtrendsapp.repos.data.ReposRepository
import lum.steve.githubtrendsapp.repos.data.remote.model.GitHubRepoDetail
import rx.Scheduler

class RepoDetailPresenter(private val reposRepository: ReposRepository, ioScheduler: Scheduler, mainScheduler: Scheduler) :
        BasePresenterImpl<RepoDetailContract.View>(ioScheduler, mainScheduler), RepoDetailContract.Presenter {


    override fun fetchRepoInfo(owner:String, repo:String) {
        addSubscription(reposRepository.fetchRepoInfo(owner, repo)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(this::handleResponse, this::handleError))
    }

    private fun handleResponse(gitHubRepoDetail: GitHubRepoDetail) {
        mView!!.showRepoDetails(gitHubRepoDetail)
    }

    private fun handleError(error: Throwable) {
        mView!!.showError()
    }
}