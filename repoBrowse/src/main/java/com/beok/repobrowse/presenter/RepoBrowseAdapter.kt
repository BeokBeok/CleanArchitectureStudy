package com.beok.repobrowse.presenter

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.beok.common.base.BaseRecyclerView
import com.beok.repobrowse.databinding.RvRepoFiletreeItemBinding
import com.beok.repobrowse.presenter.model.RepoFileTreeModel

class RepoBrowseAdapter(
    @LayoutRes
    private val layoutRes: Int,
    private val bindingId: Int,
    private val vm: RepoBrowseViewModel
) : BaseRecyclerView.Adapter<RepoFileTreeModel, RvRepoFiletreeItemBinding>(
    layoutRes,
    bindingId
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerView.ViewHolder<RvRepoFiletreeItemBinding> = ViewHolder(parent)

    inner class ViewHolder(parent: ViewGroup) :
        BaseRecyclerView.ViewHolder<RvRepoFiletreeItemBinding>(layoutRes, parent, bindingId) {

        override fun onBindViewHolder(item: Any?) {
            super.onBindViewHolder(item)
            binding.vm = this@RepoBrowseAdapter.vm
        }
    }

}