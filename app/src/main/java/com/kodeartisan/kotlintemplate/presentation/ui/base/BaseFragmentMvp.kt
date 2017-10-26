package com.tutorial.learnlinuxpro.presentation.ui.base

import com.kodeartisan.kotlintemplate.BaseApp
import com.tutorial.learnlinuxpro.presentation.di.component.DaggerFragmentComponent
import com.tutorial.learnlinuxpro.presentation.di.component.FragmentComponent
import com.tutorial.learnlinuxpro.presentation.di.module.FragmentModule
import javax.inject.Inject

/**
 * Created by kodeartisan on 18/10/17.
 */
abstract class BaseFragmentMvp<P: BaseContract.BasePresenter<V>, in V: BaseContract.BaseView> : BaseFragment(), BaseContract.BaseView {

    @Inject lateinit var mPresenter: P

    protected fun getFragmentComponent(): FragmentComponent = DaggerFragmentComponent
            .builder()
            .applicationComponent(BaseApp.appComponent)
            .build()

    protected fun getFragmentModule(): FragmentModule = FragmentModule(this)

    override fun initPresenter() {
        super.initPresenter()
        mPresenter.attachView(view = this as V)
    }

    override fun onDetach() {
        super.onDetach()
        mPresenter.detachView()
    }
}