package com.tutorial.learnlinuxpro.presentation.ui.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by kodeartisan on 12/10/17.
 */
open class RxPresenter<T: BaseContract.BaseView>: BaseContract.BasePresenter<T> {

    lateinit var mView: T
    private var mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun attachView(view: T) {
        mView = view
    }

    open fun addSubscribe(disposable: Disposable) = mCompositeDisposable.add(disposable)

    private fun unsubscribe() = mCompositeDisposable?.let { it.dispose() }

    override fun detachView() {
        unsubscribe()
    }


}