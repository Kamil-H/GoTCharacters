package com.kamilh.gotcharacters.views.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.kamilh.gotcharacters.R
import com.kamilh.gotcharacters.base.BaseFragment
import com.kamilh.gotcharacters.custom_views.ItemViewAdapter
import com.kamilh.gotcharacters.extensions.observeNotNull
import kotlinx.android.synthetic.main.fragment_characters.*
import javax.inject.Inject

class CharactersFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: CharactersViewModel
    private lateinit var adapter: ItemViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_characters, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CharactersViewModel::class.java)

        setUpView()
        setUpObservables()
    }

    private fun setUpView() {
        adapter = ItemViewAdapter(viewModel::onItemClicked)
        recyclerView.apply {
            init(LinearLayoutManager(requireContext()), viewModel::onLoadMore)
            adapter = this@CharactersFragment.adapter
        }
    }

    private fun setUpObservables() {
        observeNotNull(viewModel.isLoading) {
            recyclerView.loading = it
            progressBar.isVisible = it
        }
        observeNotNull(viewModel.items, adapter::submitList)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}
