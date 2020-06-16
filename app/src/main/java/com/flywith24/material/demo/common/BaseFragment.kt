package com.flywith24.material.demo.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.viewbinding.ViewBinding
import com.flywith24.material.demo.R

/**
 * @author Flywith24
 * @date   2020/6/15
 * time   19:39
 * description
 */
abstract class BaseFragment<T : ViewBinding>(layoutId: Int) : Fragment(layoutId) {
    private var _binding: T? = null

    val mBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = initBinding(view)
        init(savedInstanceState)
    }

    /**
     * 初始化 [_binding]
     */
    abstract fun initBinding(view: View): T

    abstract fun init(savedInstanceState: Bundle?)

    fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.container, fragment)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}