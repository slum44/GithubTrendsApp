package lum.steve.githubtrendsapp.base

import rx.Scheduler
import rx.Subscription
import rx.subscriptions.CompositeSubscription

/**
 * Created by andrewkhristyan on 10/2/16.
 */
open class BasePresenterImpl<V : BaseView>(protected var ioScheduler: Scheduler?, protected var mainScheduler: Scheduler?) : BasePresenter<V> {

    protected var mView: V? = null

    private val compositeSubscription = CompositeSubscription()

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        compositeSubscription.clear()
        mView = null
    }

    protected fun addSubscription(subscription: Subscription) {
        this.compositeSubscription.add(subscription)
    }

}