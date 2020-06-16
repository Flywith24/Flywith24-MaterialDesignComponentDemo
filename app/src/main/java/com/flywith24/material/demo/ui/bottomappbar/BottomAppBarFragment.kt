package com.flywith24.material.demo.ui.bottomappbar

import com.flywith24.material.demo.R
import com.flywith24.material.demo.bean.Demo
import com.flywith24.material.demo.ui.DemoLandingFragment

/**
 * @author Flywith24
 * @date   2020/6/16
 * time   16:50
 * description
 */
class BottomAppBarFragment : DemoLandingFragment() {
    override fun getDescription(): String = getString(R.string.bottomappbar_description)

    override fun getMainDemo(): Demo = Demo(BottomAppBarMainDemoFragment())
}