package lum.steve.githubtrendsapp

import lum.steve.githubtrendsapp.repos.data.ReposRepository
import lum.steve.githubtrendsapp.repos.data.ReposRepositoryImpl
import lum.steve.githubtrendsapp.repos.data.remote.MockGithubServiceImpl

object Injection {

    fun provideReposRepository(): ReposRepository {
        return ReposRepositoryImpl.getInstance(MockGithubServiceImpl())
    }

}