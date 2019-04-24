package com.kamilh.gotcharacters.views.character_details

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.kamilh.gotcharacters.R
import com.kamilh.gotcharacters.base.BaseFragment
import com.kamilh.gotcharacters.extensions.observeNotNull
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_character_details.*
import javax.inject.Inject

class CharacterDetailsFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: CharacterDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_character_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CharacterDetailsViewModel::class.java)

        setUpObservables()

        viewModel.onArguments(arguments?.getParcelable("args") as Arguments)
    }

    private fun setUpObservables() {
        observeNotNull(viewModel.isLoading) { horizontalProgressBar.isVisible = it }
        observeNotNull(viewModel.generalInfo) { generalInfoTitledPairListView.setUp(it) }
        observeNotNull(viewModel.tvSeries) { tvSeriesTitledPairListView.setUp(it) }
        observeNotNull(viewModel.playedBy) { playedByTitledPairListView.setUp(it) }
        observeNotNull(viewModel.books) { booksTitledPairListView.setUp(it) }
    }

    @Parcelize
    data class Arguments(
        val name: String
    ) : Parcelable {

        fun toBundle() = bundleOf(Pair("args", this))
    }
}
