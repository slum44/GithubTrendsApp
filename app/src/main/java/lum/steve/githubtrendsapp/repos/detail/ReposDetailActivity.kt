package lum.steve.githubtrendsapp.repos.detail

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_repos_detail.*
import lum.steve.githubtrendsapp.Injection
import lum.steve.githubtrendsapp.R
import lum.steve.githubtrendsapp.base.BaseActivity
import lum.steve.githubtrendsapp.repos.data.remote.model.GitHubRepoDetail
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ReposDetailActivity : BaseActivity<RepoDetailContract.View, RepoDetailContract.Presenter>(), RepoDetailContract.View {

    override var presenter: RepoDetailContract.Presenter = RepoDetailPresenter(Injection.provideReposRepository(), Schedulers.io(), AndroidSchedulers.mainThread())

    private lateinit var repoName: String
    private lateinit var ownerLogin: String
    private lateinit var ownerAvatarUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos_detail)

        repoName = intent.getStringExtra(EXTRA_REPO_NAME)
        ownerLogin = intent.getStringExtra(EXTRA_OWNER_LOGIN)
        ownerAvatarUrl = intent.getStringExtra(EXTRA_OWNER_AVATAR_URL)

        presenter.fetchRepoInfo(ownerLogin, repoName)
    }

    override fun showRepoDetails(gitHubRepoDetail: GitHubRepoDetail) {
        Picasso.with(this).load(ownerAvatarUrl).into(imageview_avatar)
        webview.loadUrl("https://github.com/${gitHubRepoDetail.owner!!.login}/${gitHubRepoDetail.name}/blob/master/README.md")

        runOnUiThread {
            title = gitHubRepoDetail.fullName
            textview_desc.text = gitHubRepoDetail.description
            textview_stars_count.text = gitHubRepoDetail.stargazersCount.toString()
            textview_forks_count.text = gitHubRepoDetail.forksCount.toString()
        }
    }

    override fun showError() {
        val snackBar = Snackbar.make(findViewById(android.R.id.content), getString(R.string.error_msg_detail), Snackbar.LENGTH_LONG).apply {
            view.setBackgroundColor(ContextCompat.getColor(view.context, android.R.color.holo_red_dark))
        }

        snackBar.show()
    }

    companion object {
        const val EXTRA_REPO_NAME = "REPO"
        const val EXTRA_OWNER_LOGIN = "OWNER"
        const val EXTRA_OWNER_AVATAR_URL = "OWNER_AVATAR_URL"
    }
}
