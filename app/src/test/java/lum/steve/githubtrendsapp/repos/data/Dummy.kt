package lum.steve.githubtrendsapp.repos.data

import lum.steve.githubtrendsapp.repos.data.remote.model.GitHubRepoDetail
import lum.steve.githubtrendsapp.repos.data.remote.model.GitHubSearchResults
import lum.steve.githubtrendsapp.repos.data.remote.model.Item

object Dummy {
    const val REPO_ONE_NAME = "repo1"
    const val REPO_TWO_NAME = "repo2"

    @JvmStatic
    fun getGitHubSearchResults(): GitHubSearchResults {
        return GitHubSearchResults().apply {
            items = listOf(
                    Item().apply { name = REPO_ONE_NAME },
                    Item().apply { name = REPO_TWO_NAME }
            )

        }
    }

    @JvmStatic
    fun getRepoDetailInfo(): GitHubRepoDetail {
        return GitHubRepoDetail().apply {
            name = "retrofit"
        }
    }

}