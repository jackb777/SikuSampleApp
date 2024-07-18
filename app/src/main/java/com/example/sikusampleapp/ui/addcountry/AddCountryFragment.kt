package com.example.sikusampleapp.ui.addcountry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sikusampleapp.R
import com.example.sikusampleapp.databinding.FragmentAddCountryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCountryFragment : Fragment(R.layout.fragment_add_country) {

    private lateinit var binding: FragmentAddCountryBinding
    companion object {
        const val ADD_COUNTRY_FRAGMENT_TAG = "ADD_COUNTRY_FRAGMENT_TAG"
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCountryBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}