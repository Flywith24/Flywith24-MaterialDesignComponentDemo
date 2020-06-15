package com.flywith24.material.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.math.MathUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.flywith24.material.demo.bean.HostBean
import com.flywith24.material.demo.common.BaseFragment
import com.flywith24.material.demo.databinding.FragmentHostBinding
import com.flywith24.material.demo.databinding.ItemHostBinding
import com.flywith24.material.demo.views.GridDividerDecoration
import org.jetbrains.annotations.NotNull

/**
 * @author Flywith24
 * @date   2020/6/15
 * time   19:25
 * description
 */
class HostFragment : BaseFragment<FragmentHostBinding>(R.layout.fragment_host) {

    override fun initBinding(view: View): FragmentHostBinding = FragmentHostBinding.bind(view)

    override fun init(savedInstanceState: Bundle?) {
        with(mBinding.recyclerView) {
            val gridSpanCount = calculateGridSpanCount()
            layoutManager = GridLayoutManager(requireContext(), gridSpanCount)
            addItemDecoration(
                GridDividerDecoration(
                    resources.getDimensionPixelSize(R.dimen.grid_divider_size),
                    ContextCompat.getColor(context, R.color.grid_divider_color),
                    gridSpanCount
                )
            )
        }
    }

    private fun calculateGridSpanCount(): Int {
        val displayMetrics = resources.displayMetrics
        val displayWidth = displayMetrics.widthPixels
        val itemSize = resources.getDimensionPixelSize(R.dimen.item_size)
        val gridSpanCount = displayWidth / itemSize
        return MathUtils.clamp(gridSpanCount, 1, 4)
    }

    class HostAdapter : ListAdapter<HostBean, HostAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<HostBean>() {
        override fun areItemsTheSame(oldItem: HostBean, newItem: HostBean): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: HostBean, newItem: HostBean): Boolean =
            oldItem.title == newItem.title && oldItem.drawableResId == newItem.drawableResId
    }) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemBinding =
                ItemHostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(itemBinding)
        }


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(getItem(position))
        }

        class ViewHolder(private val itemBinding: ItemHostBinding) :
            RecyclerView.ViewHolder(itemBinding.root) {
            fun bind(item: HostBean) {
                itemBinding.title.text = item.title
            }
        }
    }
}