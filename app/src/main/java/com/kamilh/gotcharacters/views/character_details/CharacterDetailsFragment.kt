package com.kamilh.gotcharacters.views.character_details

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.kamilh.gotcharacters.R
import com.kamilh.gotcharacters.base.BaseFragment
import kotlinx.android.parcel.Parcelize
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

        setUpView()
        setUpObservables()
    }

    private fun setUpView() {
    }

    private fun setUpObservables() {
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    @Parcelize
    data class Arguments(
        val name: String
    ) : Parcelable {

        fun toBundle() = bundleOf(Pair("args", this))
    }
}
