package com.flywith24.material.demo.bean

import com.flywith24.material.demo.common.Const

/**
 * @author Flywith24
 * @date   2020/6/16
 * time   16:08
 * description
 */
data class Demo(val title: String) : MultiType {
    override fun getType(): Int = Const.TYPE_DEMO
}
