package com.example.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * BaseActivity
 *
 * @author (c) 2024, Hugo Figueroa
 * */
abstract class BaseActivity<VB: ViewBinding, VM: BaseViewModel>: AppCompatActivity() {

    /**
     * This ViewModel will allow us to keep the logic separate from our UI.
     *
     * Example of implementation:
     *  override val viewModel: EmptyViewModel by viewModels()
     */
    abstract val viewModel: VM

    /**
     * This ViewBinding will allow us to create our View
     *
     * Example of implementation:
     *  override val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)
     *
     *  or
     *
     *  override val binding by viewBinding(ActivityMainBinding::inflate)
     */
    abstract val binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        addViewModelObservables()
    }

    /**
     * Override it to be able to configure the view together with the associated ViewBinding.
     * Example:
     *  override fun initView() = with(binding) {
     *      someTextView.text = "Binding!!"
     *  }
     */
    protected open fun initView() = Unit

    /**
     * This method allow us to add observers to our ViewModel events
     * Example:
     *  override fun addViewModelObservables() = with(baseViewModel) {
     *      observe(foo1()) { someAction1() }
     *      observe(foo2()) { someAction2() }
     *  }
     */
    protected open fun addViewModelObservables() = Unit
}