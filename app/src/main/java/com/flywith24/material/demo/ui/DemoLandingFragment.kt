package com.flywith24.material.demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.flywith24.material.demo.R
import com.flywith24.material.demo.bean.Demo
import com.flywith24.material.demo.bean.DemoTitle
import com.flywith24.material.demo.bean.MultiType
import com.flywith24.material.demo.common.BaseFragment
import com.flywith24.material.demo.common.Const
import com.flywith24.material.demo.common.ViewBindingViewHolder
import com.flywith24.material.demo.databinding.FragmentDemoLandingBinding
import com.flywith24.material.demo.databinding.ItemDemoBinding
import com.flywith24.material.demo.databinding.ItemDemoTitleBinding

/**
 * @author Flywith24
 * @date   2020/6/16
 * time   15:58
 * description demo 首页 公共类
 */
open class DemoLandingFragment :
    BaseFragment<FragmentDemoLandingBinding>(R.layout.fragment_demo_landing) {
    override fun initBinding(view: View): FragmentDemoLandingBinding =
        FragmentDemoLandingBinding.bind(view)

    private val mAdapter by lazy { DemoLandingAdapter() }

    override fun init(savedInstanceState: Bundle?) {
        mBinding.recyclerView.adapter = mAdapter
        val list = ArrayList(
            listOf<MultiType>(
                DemoTitle(getString(R.string.description)),
                DemoTitle(getDescription())
            )
        )
        getMainDemo()?.let { mainDemo ->
            list.add(mainDemo)
        }
        if (getAdditionalDemos().isNotEmpty()) {
            list.add(DemoTitle(getString(R.string.other_demo)))
            list.addAll(getAdditionalDemos())
        }
        mAdapter.submitList(list)
    }

    open fun getDescription(): String = ""

    open fun getMainDemo(): Demo? = null

    open fun getAdditionalDemos(): List<Demo> = emptyList()

    inner class DemoLandingAdapter :
        ListAdapter<MultiType, ViewBindingViewHolder<ViewBinding>>(object :
            DiffUtil.ItemCallback<MultiType>() {
            override fun areItemsTheSame(oldItem: MultiType, newItem: MultiType): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: MultiType, newItem: MultiType): Boolean =
                if (oldItem is Demo && newItem is Demo) {
                    oldItem.title == newItem.title
                } else if (oldItem is DemoTitle && newItem is DemoTitle) {
                    oldItem.title == newItem.title
                } else {
                    false
                }
        }) {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewBindingViewHolder<ViewBinding> {
            val demoHolder = ViewBindingViewHolder(
                ItemDemoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            return when (viewType) {
                Const.TYPE_DEMO -> {
                    demoHolder.viewBinding.root.setOnClickListener {
                        val item = getItem(demoHolder.bindingAdapterPosition) as Demo
                        item.createFragment()?.let { fragment ->
                            replaceFragment(fragment)
                        }
                    }
                    demoHolder
                }
                Const.TYPE_DEMO_TITLE -> {
                    ViewBindingViewHolder(
                        ItemDemoTitleBinding.inflate(
                            LayoutInflater.from(parent.context),
                            parent,
                            false
                        )
                    )
                }
                else -> {
                    demoHolder
                }
            }
        }

        override fun getItemViewType(position: Int): Int = getItem(position).getType()

        override fun onBindViewHolder(holder: ViewBindingViewHolder<ViewBinding>, position: Int) {
            when (val binding = holder.viewBinding) {
                is ItemDemoBinding -> {
                    val item = getItem(position) as Demo
                    binding.rowTitle.text = item.title
                    binding.rowSubtitle.text = item.createFragment()?.javaClass?.simpleName
                }
                is ItemDemoTitleBinding -> {
                    val item = getItem(position) as DemoTitle
                    binding.rowTitle.text = item.title
                }
                else -> {
                    // do nothing
                }
            }
        }
    }
}