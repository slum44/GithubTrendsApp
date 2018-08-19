package lum.steve.githubtrendsapp.repos.data.remote

import lum.steve.githubtrendsapp.repos.data.remote.model.GitHubRepoDetail
import lum.steve.githubtrendsapp.repos.data.remote.model.GitHubSearchResults
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface GithubService {

    @GET("/search/repositories")
    fun loadTrendingRepos(@Query("sort") sort:String, @Query("order") order:String, @Query("q") q: String): Observable<GitHubSearchResults>

    @GET("/repos/{owner}/{repo}")
    fun fetchRepoInfo(@Path("owner") owner: String, @Path("repo") repo: String): Observable<GitHubRepoDetail>

}