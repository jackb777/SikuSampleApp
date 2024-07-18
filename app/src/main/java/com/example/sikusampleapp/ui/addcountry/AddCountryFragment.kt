package com.example.sikusampleapp.ui.addcountry

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.TextView.BufferType.SPANNABLE
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

        with(binding) {
            addCountryButton.setOnClickListener {
                updateText()
            }
        }
    }

    private fun updateText() {
        val text = binding.addCountryTitle.text
        val builder = SpannableStringBuilder()
        val spannable = SpannableString(text)
        spannable.setSpan(ForegroundColorSpan(Color.BLUE), 7, 12, 0)
        spannable.setSpan(ForegroundColorSpan(Color.BLUE), 24, text.length, 0)
        builder.append(spannable)

        binding.addCountryTitle.setText(builder, SPANNABLE)
    }
}