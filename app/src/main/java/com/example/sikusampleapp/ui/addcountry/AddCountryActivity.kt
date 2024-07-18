package com.example.sikusampleapp.ui.addcountry

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.sikusampleapp.databinding.ActivityAddCountryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCountryActivity: FragmentActivity() {

    private lateinit var binding: ActivityAddCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val fragment = AddCountryFragment()
            supportFragmentManager
                .beginTransaction()
                .add(binding.addCountryFragmentContainer.id, fragment)
                .commit()
        }
    }
}