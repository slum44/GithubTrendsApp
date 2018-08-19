package lum.steve.githubtrendsapp.repos.data

import lum.steve.githubtrendsapp.repos.data.remote.GithubService
import lum.steve.githubtrendsapp.repos.data.remote.model.GitHubRepoDetail
import lum.steve.githubtrendsapp.repos.data.remote.model.GitHubSearchResults
import rx.Observable

class ReposRepositoryImpl(val githubService: GithubService) : ReposRepository {

    override fun searchRepos(q: String): Observable<GitHubSearchResults> {
        return Observable.defer { githubService.loadTrendingRepos("stars", "desc", q) }
    }

    override fun fetchRepoInfo(owner: String, repo:String): Observable<GitHubRepoDetail> {
        return Observable.defer { githubService.fetchRepoInfo(owner, repo) }
    }

    companion object {

        private var INSTANCE: ReposRepository? = null

        /**
         * Singleton instantiaton
         */
        @JvmStatic
        fun getInstance(githubService: GithubService): ReposRepository {
            return INSTANCE ?: ReposRepositoryImpl(githubService = githubService)
                    .apply { INSTANCE = this }
        }
    }

}