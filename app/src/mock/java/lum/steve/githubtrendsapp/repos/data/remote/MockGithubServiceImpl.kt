package lum.steve.githubtrendsapp.repos.data.remote

import lum.steve.githubtrendsapp.repos.data.remote.model.GitHubRepoDetail
import lum.steve.githubtrendsapp.repos.data.remote.model.GitHubSearchResults
import lum.steve.githubtrendsapp.repos.data.remote.model.Item
import lum.steve.githubtrendsapp.repos.data.remote.model.Owner
import rx.Observable

class MockGithubServiceImpl : GithubService {

    override fun loadTrendingRepos(sort: String, order: String, q: String): Observable<GitHubSearchResults> {
        return Observable.just(getGitHubSearchResults())
    }

    override fun fetchRepoInfo(owner: String, repo: String): Observable<GitHubRepoDetail> {
        return Observable.just(getRepoDetailInfo())
    }

    private fun getGitHubSearchResults(): GitHubSearchResults {
        return GitHubSearchResults().apply {
            items = listOf(
                    Item().apply {
                        name = "repo1"
                        description = "repo1 desc"
                        owner = Owner().apply {
                            avatarUrl = "https://avatars1.githubusercontent.com/u/1342004?v=4"
                        }
                    },
                    Item().apply {
                        name = "repo2"
                        description = "repo2 desc"
                        owner = Owner().apply {
                            avatarUrl = "https://avatars1.githubusercontent.com/u/1342004?v=4"
                        }
                    }
            )

        }
    }

    private fun getRepoDetailInfo(): GitHubRepoDetail {
        return GitHubRepoDetail().apply {
            name = "retrofit"
        }
    }

}