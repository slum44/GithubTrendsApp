package lum.steve.githubtrendsapp

import lum.steve.githubtrendsapp.repos.data.ReposRepository
import lum.steve.githubtrendsapp.repos.data.ReposRepositoryImpl
import lum.steve.githubtrendsapp.repos.data.remote.GithubService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Injection {

    private const val BASE_URL = "https://api.github.com"
    private var githubService : GithubService? = null

    init {
        val retrofit = initRetrofit()
        initServices(retrofit)
    }

    private fun initRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return Retrofit.Builder()
                .client(OkHttpClient.Builder().addInterceptor(logging).build())
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun initServices(retrofit: Retrofit) {
        githubService = retrofit.create(GithubService::class.java)
    }

    fun provideReposRepository(): ReposRepository {
        return ReposRepositoryImpl.getInstance(githubService!!)
    }

}