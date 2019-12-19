package com.beok.reposearch.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.beok.reposearch.databinding.RvRepoItemBinding
import com.beok.reposearch.presenter.model.ReposModel

class RepoSearchAdapter(
    @LayoutRes
    private val layoutRes: Int,
    private val bindingId: Int?,
    private val viewModel: RepoSearchViewModel
) : PagedListAdapter<ReposModel, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoSearchViewHolder =
        RepoSearchViewHolder(layoutRes, parent, bindingId)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            (holder as RepoSearchViewHolder).bind(it)
        }
    }

    inner class RepoSearchViewHolder(
        @LayoutRes
        private val resource: Int,
        parent: ViewGroup,
        private val bindingId: Int?
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(resource, parent, false)
    ) {
        private val binding: RvRepoItemBinding = DataBindingUtil.bind(itemView)!!

        fun bind(item: ReposModel) {
            if (bindingId == null) return

            binding.run {
                setVariable(bindingId, item)
            }
            binding.vm = viewModel
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<ReposModel>() {
            override fun areItemsTheSame(oldItem: ReposModel, newItem: ReposModel): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: ReposModel, newItem: ReposModel): Boolean =
                oldItem == newItem
        }
    }
}