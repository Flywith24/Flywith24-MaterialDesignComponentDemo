package com.flywith24.material.demo.bean

import androidx.fragment.app.Fragment
import com.flywith24.material.demo.common.Const

/**
 * @author Flywith24
 * @date   2020/6/16
 * time   16:08
 * description
 */
data class Demo(val fragment: Fragment? = null, val title: String = "Demo") : MultiType {
    override fun getType(): Int = Const.TYPE_DEMO

    fun createFragment(): Fragment? = fragment
}
