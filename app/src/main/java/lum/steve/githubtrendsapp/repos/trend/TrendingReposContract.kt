package lum.steve.githubtrendsapp.repos.trend

import lum.steve.githubtrendsapp.base.BasePresenter
import lum.steve.githubtrendsapp.base.BaseView
import lum.steve.githubtrendsapp.repos.data.remote.model.GitHubSearchResults
import lum.steve.githubtrendsapp.repos.data.remote.model.Item

interface TrendingReposContract {

    interface View : BaseView {
        fun setLoadingIndicator(active: Boolean)
        fun showResults(reposList: GitHubSearchResults)
        fun showError()
        fun showRepoDetailsScreen(item: Item)
    }

    interface Presenter : BasePresenter<View> {
        fun loadTrendingRepos()
        fun onItemClick(item: Item)
    }
}