package lum.steve.githubtrendsapp.repos.trend

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_trending_repos.*
import kotlinx.android.synthetic.main.rv.*
import lum.steve.githubtrendsapp.Injection
import lum.steve.githubtrendsapp.R
import lum.steve.githubtrendsapp.base.BaseActivity
import lum.steve.githubtrendsapp.repos.ReposAdapter
import lum.steve.githubtrendsapp.repos.data.remote.model.GitHubSearchResults
import lum.steve.githubtrendsapp.repos.data.remote.model.Item
import lum.steve.githubtrendsapp.repos.detail.ReposDetailActivity
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class TrendingReposActivity : BaseActivity<TrendingReposContract.View, TrendingReposContract.Presenter>(), TrendingReposContract.View {

    override var presenter: TrendingReposContract.Presenter = TrendingReposPresenter(
            Injection.provideReposRepository(), Schedulers.io(), AndroidSchedulers.mainThread())

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var rv: RecyclerView
    private lateinit var reposAdapter: ReposAdapter


    private var clickListener: OnItemClickListener = object : OnItemClickListener {
        override fun onItemClick(item: Item) {
            presenter.onItemClick(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trending_repos)

        swipeRefreshLayout = swipe_container.apply {
            setColorSchemeResources(android.R.color.holo_blue_bright,
                    android.R.color.holo_red_light,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light)

            setOnRefreshListener {
                presenter.loadTrendingRepos()
            }
        }

        reposAdapter = ReposAdapter(null, this, clickListener)
        rv = findViewById<RecyclerView>(R.id.rv).apply {
            adapter = reposAdapter
        }

        setLoadingIndicator(true)
        presenter.loadTrendingRepos()
    }

    override fun showResults(reposList: GitHubSearchResults) {
        reposAdapter.setItems(reposList)
    }

    override fun setLoadingIndicator(active: Boolean) {
        swipeRefreshLayout.isRefreshing = active
    }

    override fun showRepoDetailsScreen(item: Item) {
        val intent = Intent(this, ReposDetailActivity::class.java).apply {
            putExtra(ReposDetailActivity.EXTRA_REPO_NAME, item.name)
            putExtra(ReposDetailActivity.EXTRA_OWNER_LOGIN, item.owner!!.login)
            putExtra(ReposDetailActivity.EXTRA_OWNER_AVATAR_URL, item.owner!!.avatarUrl)
        }
        startActivity(intent)
    }

    override fun showError() {
        val snackbar = Snackbar.make(coordLayout, getString(R.string.error_msg_load_repos), Snackbar.LENGTH_LONG).apply {
            view.setBackgroundColor(ContextCompat.getColor(view.context, android.R.color.holo_red_dark))
        }

        snackbar.show()
    }

    interface OnItemClickListener {

        fun onItemClick(item: Item)
    }

}
