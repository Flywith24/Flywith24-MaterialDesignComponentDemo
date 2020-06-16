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
import com.flywith24.material.demo.bean.HostBean
import com.flywith24.material.demo.common.BaseFragment
import com.flywith24.material.demo.common.ViewBindingViewHolder
import com.flywith24.material.demo.common.setTopDrawable
import com.flywith24.material.demo.databinding.FragmentHostBinding
import com.flywith24.material.demo.databinding.ItemHostBinding
import com.flywith24.material.demo.ui.bottomappbar.BottomAppBarFragment
import com.flywith24.material.demo.views.GridDividerDecoration

/**
 * @author Flywith24
 * @date   2020/6/15
 * time   19:25
 * description
 */
class HostFragment : BaseFragment<FragmentHostBinding>(R.layout.fragment_host),
    OnItemClickListener {

    private val mAdapter by lazy { HostAdapter(this) }

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
            adapter = mAdapter
        }

        val list = listOf(
            HostBean("Bottom App Bar", R.drawable.ic_bottomappbar),
            HostBean("Bottom Navigation", R.drawable.ic_bottomnavigation),
            HostBean("Bottom Sheet", R.drawable.ic_bottomsheet),
            HostBean("Buttons", R.drawable.ic_button),
            HostBean("Cards", R.drawable.ic_card),
            HostBean("Checkbox", R.drawable.ic_checkbox),
            HostBean("Chips", R.drawable.ic_chips),
            HostBean("Pickers", R.drawable.ic_dialog),
            HostBean("Dialogs", R.drawable.ic_dialog),
            HostBean("Elevation and Shadow", R.drawable.ic_elevation),
            HostBean("Floating Action Button", R.drawable.ic_fab),
            HostBean("Fonts", R.drawable.ic_fonts)
        )

        mAdapter.submitList(list)
    }

    private fun calculateGridSpanCount(): Int {
        val displayMetrics = resources.displayMetrics
        val displayWidth = displayMetrics.widthPixels
        val itemSize = resources.getDimensionPixelSize(R.dimen.item_size)
        val gridSpanCount = displayWidth / itemSize
        return MathUtils.clamp(gridSpanCount, 1, 4)
    }


    override fun onItemClick(item: HostBean) {
        replaceFragment(BottomAppBarFragment())
    }

    class HostAdapter(private val listener: OnItemClickListener) :
        ListAdapter<HostBean, ViewBindingViewHolder<ItemHostBinding>>(object :
            DiffUtil.ItemCallback<HostBean>() {
            override fun areItemsTheSame(oldItem: HostBean, newItem: HostBean): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: HostBean, newItem: HostBean): Boolean =
                oldItem.title == newItem.title && oldItem.drawableResId == newItem.drawableResId
        }) {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewBindingViewHolder<ItemHostBinding> {
            val itemBinding =
                ItemHostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            val holder = ViewBindingViewHolder(itemBinding)
            itemBinding.root.setOnClickListener {
                listener.onItemClick(getItem(holder.bindingAdapterPosition))
            }
            return holder
        }


        override fun onBindViewHolder(
            holder: ViewBindingViewHolder<ItemHostBinding>,
            position: Int
        ) {
            val item = getItem(position)
            with(holder.viewBinding.title) {
                text = item.title
                setTopDrawable(item.drawableResId)
            }
        }
    }
}

interface OnItemClickListener {
    fun onItemClick(item: HostBean)
}