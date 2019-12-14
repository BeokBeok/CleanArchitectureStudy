package com.beok.reposearch.presenter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import com.beok.common.base.BaseFragment
import com.beok.reposearch.BR
import com.beok.reposearch.R
import com.beok.reposearch.databinding.FragmentRepoSearchBinding
import com.facebook.shimmer.Shimmer
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import java.util.concurrent.TimeUnit

class RepoSearchFragment : BaseFragment<FragmentRepoSearchBinding, RepoSearchViewModel>(
    R.layout.fragment_repo_search
) {

    override val viewModel: RepoSearchViewModel by viewModel()
    private val repoSearchAdapter by lazy {
        RepoSearchAdapter(
            R.layout.rv_repo_item,
            BR.repo,
            viewModel
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initBinding()
        initRecyclerView()
        initAdMob()
        setTextChangedListener()
        setObserve()
    }

    override fun onResume() {
        super.onResume()
        binding.sflLoading.startShimmer()
    }

    override fun onPause() {
        super.onPause()
        binding.sflLoading.stopShimmer()
    }

    override fun initBinding() {
        binding.vm = viewModel
    }

    private fun initRecyclerView() {
        binding.rvContents.run {
            setHasFixedSize(true)
            adapter = repoSearchAdapter
        }
        viewModel.repoList.observe(
            viewLifecycleOwner,
            Observer {
                if (it.size == 0) showShimmer() else hideShimmer()
                repoSearchAdapter.submitList(it)
            }
        )
    }

    private fun setTextChangedListener() {
        addDisposable(
            Observable.create<CharSequence> { emitter ->
                binding.etSearch.addTextChangedListener(object : TextWatcher {
                    override fun onTextChanged(
                        char: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        if (char != null) emitter.onNext(char)
                    }

                    override fun afterTextChanged(s: Editable?) = Unit

                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) = Unit
                })
            }.debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        binding.rvContents.scrollToPosition(0)
                        viewModel.searchUserRepo(it.toString().toLowerCase(Locale.getDefault()))
                    },
                    onError = {
                        showSnackBar(it.message)
                        hideShimmer()
                    }
                )
        )
    }

    private fun setObserve() {
        viewModel.errMsg.observe(
            viewLifecycleOwner,
            Observer {
                showSnackBar(it.message)
                hideShimmer()
            }
        )
    }

    private fun initAdMob() {
        MobileAds.initialize(requireContext())
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    private fun showShimmer() {
        binding.sflLoading.run {
            visibility = View.VISIBLE
            val shimmer = Shimmer.AlphaHighlightBuilder()
                .setDirection(Shimmer.Direction.TOP_TO_BOTTOM)
                .setTilt(0f)
                .build()
            setShimmer(shimmer)
        }
    }

    private fun hideShimmer() {
        binding.sflLoading.run {
            stopShimmer()
            visibility = View.GONE
        }
    }
}