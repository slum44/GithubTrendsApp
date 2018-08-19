package lum.steve.githubtrendsapp.repos.detail

import lum.steve.githubtrendsapp.base.BasePresenter
import lum.steve.githubtrendsapp.base.BaseView
import lum.steve.githubtrendsapp.repos.data.remote.model.GitHubRepoDetail

interface RepoDetailContract {

    interface View : BaseView {
//        fun setLoadingIndicator(active: Boolean)
//        fun showResults(reposList: GitHubSearchResults)
        fun showError()
        fun showRepoDetails(gitHubRepoDetail: GitHubRepoDetail)
    }

    interface Presenter : BasePresenter<View> {
        fun fetchRepoInfo(owner:String, repo:String)
    }
}