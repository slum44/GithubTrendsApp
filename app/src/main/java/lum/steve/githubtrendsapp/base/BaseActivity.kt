package lum.steve.githubtrendsapp.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<in V : BaseView, T : BasePresenter<V>> : AppCompatActivity(), BaseView {

    protected abstract var presenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.attachView(this as V)
    }

    override fun getContext(): Context {
        return this
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}
