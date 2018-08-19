package lum.steve.githubtrendsapp.base

interface BasePresenter<in V : BaseView> {

    fun attachView(view: V)

    fun detachView()
}