package com.example.core.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.core.base.viewModel.BaseViewModel

/**
 * BaseFragment
 *
 * @author (c) 2024, Hugo Figueroa
 * */
abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment()  {

    /**
     * This ViewModel will allow us to keep the logic separate from our UI.
     *
     * Example of implementation:
     * override val viewModel: MainViewModel by viewModels()
     */
    abstract val viewModel: VM

    /**
     * This ViewBinding will allow us to create our View
     *
     * Example of implementation:
     * override val binding: MainFragmentBinding by viewBinding(MainFragmentBinding::inflate)
     */
    abstract val binding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        addClicks()
        setUp(arguments)
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
     * Override it to be able to initialize the logic with received Bundle data
     */
    protected open fun setUp(arguments: Bundle?) {
        viewModel.setUp(arguments)
    }

    /**
     * This method allow us to add observers to our ViewModel events
     * Example:
     *  override fun addViewModelObservables() = with(baseViewModel) {
     *      observe(foo1()) { someAction1() }
     *      observe(foo2()) { someAction2() }
     *  }
     */
    protected open fun addViewModelObservables() = Unit

    protected open fun addClicks() = Unit
}