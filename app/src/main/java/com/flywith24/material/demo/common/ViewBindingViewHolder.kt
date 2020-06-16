package com.flywith24.material.demo.common

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * @author Flywith24
 * @date   2020/6/16
 * time   16:25
 * description
 */
class ViewBindingViewHolder<out T : ViewBinding>(val viewBinding: T) :
    RecyclerView.ViewHolder(viewBinding.root)