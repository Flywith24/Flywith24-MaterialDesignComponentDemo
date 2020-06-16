package com.flywith24.material.demo.common

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.viewbinding.ViewBinding
import com.flywith24.material.demo.R
import com.google.android.material.color.MaterialColors
import com.google.android.material.transition.MaterialContainerTransform

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

    @SuppressLint("PrivateResource")
    fun replaceFragment(fragment: Fragment, sharedElement: View, sharedElementName: String) {
        ViewCompat.setTransitionName(sharedElement, sharedElementName)
        fragment.sharedElementEnterTransition = MaterialContainerTransform().apply {
            containerColor = MaterialColors.getColor(sharedElement, R.attr.colorSurface)
            fadeMode = MaterialContainerTransform.FADE_MODE_THROUGH
        }

        parentFragmentManager.commit {
            addSharedElement(sharedElement, sharedElementName)
            setCustomAnimations(
                R.anim.abc_grow_fade_in_from_bottom,
                R.anim.abc_fade_out,
                R.anim.abc_fade_in,
                R.anim.abc_shrink_fade_out_from_bottom
            )
            addToBackStack(null)
            replace(R.id.container, fragment)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}