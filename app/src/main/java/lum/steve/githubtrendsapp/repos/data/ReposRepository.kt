package lum.steve.githubtrendsapp.repos.data

import lum.steve.githubtrendsapp.repos.data.remote.model.GitHubRepoDetail
import lum.steve.githubtrendsapp.repos.data.remote.model.GitHubSearchResults
import rx.Observable

interface ReposRepository {
    fun searchRepos(q: String): Observable<GitHubSearchResults>
    fun fetchRepoInfo(owner: String, repo: String): Observable<GitHubRepoDetail>
}