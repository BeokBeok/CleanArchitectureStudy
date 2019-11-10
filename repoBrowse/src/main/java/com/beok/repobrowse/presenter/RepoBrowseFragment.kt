package com.beok.repobrowse.presenter


import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.beok.common.base.BaseFragment
import com.beok.repobrowse.BR
import com.beok.repobrowse.R
import com.beok.repobrowse.databinding.FragmentRepoBrowseBinding
import com.beok.repobrowse.databinding.RvRepoFiletreeItemBinding
import com.beok.repobrowse.domain.entity.RepoFileTreeEntity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoBrowseFragment : BaseFragment<FragmentRepoBrowseBinding, RepoBrowseViewModel>(
    R.layout.fragment_repo_browse
) {
    override val viewModel: RepoBrowseViewModel by viewModel()
    private val args: RepoBrowseFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initBinding()
        initRecyclerView()
        showContents()
        setObserveErrMsg()
    }

    override fun initBinding() {
        binding.vm = viewModel
    }

    private fun initRecyclerView() {
        binding.rvFiletree.run {
            setHasFixedSize(true)
            adapter =
                RepoBrowseAdapter<List<RepoFileTreeEntity>, RvRepoFiletreeItemBinding>(
                    R.layout.rv_repo_filetree_item,
                    BR.repoFileTreeItem,
                    viewModel
                )
        }
    }

    private fun initSpinner(branches: List<String>) {
        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            branches
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spBranch.adapter = adapter
        }
    }

    private fun showContents() {
        viewModel.showRepoBrowser(
            args.user,
            args.repoName,
            null
        )
        viewModel.branch.observe(
            viewLifecycleOwner,
            Observer {
                initSpinner(it)
            }
        )
    }

    private fun setObserveErrMsg() {
        viewModel.errMsg.observe(
            viewLifecycleOwner,
            Observer {
                showSnackBar(it.message ?: "")
            }
        )
    }

}